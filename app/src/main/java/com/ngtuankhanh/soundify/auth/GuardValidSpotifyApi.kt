package com.ngtuankhanh.soundify.auth

import android.app.Activity
import com.adamratzman.spotify.SpotifyClientApi
import com.adamratzman.spotify.SpotifyException
import com.adamratzman.spotify.auth.pkce.startSpotifyClientPkceLoginActivity
import com.ngtuankhanh.soundify.ui.Model
import com.ngtuankhanh.soundify.ui.activities.MainActivity
import kotlinx.coroutines.runBlocking

fun <T> Activity.guardValidSpotifyApi(
    classBackTo: Class<out Activity> = MainActivity::class.java,
    alreadyTriedToReauthenticate: Boolean = false,
    block: suspend (api: SpotifyClientApi) -> T
): T? {
    return runBlocking {
        try {
            val token = Model.credentialStore.spotifyToken
                ?: throw SpotifyException.ReAuthenticationNeededException()
            val api = Model.credentialStore.getSpotifyClientPkceApi()
                ?: throw SpotifyException.ReAuthenticationNeededException()

            block(api)
        } catch (e: SpotifyException) {
            e.printStackTrace()
            val api = Model.credentialStore.getSpotifyClientPkceApi()!!
            if (!alreadyTriedToReauthenticate) {
                try {
                    api.refreshToken()
                    Model.credentialStore.spotifyToken = api.token
                    block(api)
                } catch (e: SpotifyException.ReAuthenticationNeededException) {
                    e.printStackTrace()
                    return@runBlocking guardValidSpotifyApi(
                        classBackTo = classBackTo,
                        alreadyTriedToReauthenticate = true,
                        block = block
                    )
                } catch (e: IllegalArgumentException) {
                    e.printStackTrace()
                    return@runBlocking guardValidSpotifyApi(
                        classBackTo = classBackTo,
                        alreadyTriedToReauthenticate = true,
                        block = block
                    )
                }
            } else {
                pkceClassBackTo = classBackTo
                startSpotifyClientPkceLoginActivity(SpotifyPkceLoginActivityImpl::class.java)
                null
            }
        }
    }
}
