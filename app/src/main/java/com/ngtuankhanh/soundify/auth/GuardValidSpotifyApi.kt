package com.ngtuankhanh.soundify.auth

import android.app.Activity
import com.adamratzman.spotify.SpotifyClientApi
import com.adamratzman.spotify.SpotifyException
import com.adamratzman.spotify.auth.SpotifyDefaultCredentialStore
import com.adamratzman.spotify.auth.implicit.startSpotifyImplicitLoginActivity
import com.adamratzman.spotify.auth.pkce.startSpotifyClientPkceLoginActivity
import com.ngtuankhanh.soundify.ui.SoundifyApplication
import com.ngtuankhanh.soundify.ui.SpotifyApi
import kotlinx.coroutines.runBlocking

fun <T> guardValidSpotifyApi(
    alreadyTriedToReauthenticate: Boolean = false,
    block: suspend (api: SpotifyClientApi) -> T
): T? {
    val credentialStore = SpotifyApi.credentialStore
    return runBlocking {
        try {
            val token = credentialStore.spotifyToken
                ?: throw SpotifyException.ReAuthenticationNeededException()
            val usesPkceAuth = token.refreshToken != null
            val api = (if (usesPkceAuth) credentialStore.getSpotifyClientPkceApi()
            else credentialStore.getSpotifyImplicitGrantApi())
                ?: throw SpotifyException.ReAuthenticationNeededException()

            block(api)
        } catch (e: SpotifyException) {
            e.printStackTrace()
            val usesPkceAuth = credentialStore.spotifyToken?.refreshToken != null
            if (usesPkceAuth) {
                val api = credentialStore.getSpotifyClientPkceApi()!!
                if (!alreadyTriedToReauthenticate) {
                    try {
                        api.refreshToken()
                        credentialStore.spotifyToken = api.token
                        block(api)
                    } catch (e: SpotifyException.ReAuthenticationNeededException) {
                        e.printStackTrace()
                        return@runBlocking guardValidSpotifyApi(
                            alreadyTriedToReauthenticate = true,
                            block = block
                        )
                    } catch (e: IllegalArgumentException) {
                        e.printStackTrace()
                        return@runBlocking guardValidSpotifyApi(
                            alreadyTriedToReauthenticate = true,
                            block = block
                        )
                    }
                } else {
//                    pkceNavigateTo = navigateTo
//                    startSpotifyClientPkceLoginActivity(PkceLoginActivityImpl::class.java)
                    null
                }
            } else {
//                SpotifyDefaultCredentialStore.activityBackOnImplicitAuth = navigateTo
//                startSpotifyImplicitLoginActivity(ImplicitLoginActivityImpl::class.java)
                null
            }
        }
    }
}

