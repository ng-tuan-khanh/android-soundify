package com.ngtuankhanh.soundify.auth

import android.app.Activity
import android.content.Intent
import com.adamratzman.spotify.SpotifyClientApi
import com.adamratzman.spotify.SpotifyScope
import com.adamratzman.spotify.auth.pkce.AbstractSpotifyPkceLoginActivity
import com.ngtuankhanh.soundify.BuildConfig
import com.ngtuankhanh.soundify.ui.activities.MainActivity
import com.ngtuankhanh.soundify.ui.SoundifyApplication
import com.ngtuankhanh.soundify.ui.SpotifyApi

internal var pkceNavigateTo: Class<out Activity>? = null
class PkceLoginActivityImpl : AbstractSpotifyPkceLoginActivity() {
    override val clientId = BuildConfig.SPOTIFY_CLIENT_ID
    override val redirectUri = BuildConfig.SPOTIFY_REDIRECT_URI_PKCE
    override val scopes = SpotifyScope.values().toList()

    override fun onSuccess(api: SpotifyClientApi) {
        val credentialStore = SpotifyApi.credentialStore
        credentialStore.setSpotifyApi(api)
        val navigateTo = pkceNavigateTo ?: MainActivity::class.java
        pkceNavigateTo = null
        startActivity(Intent(this, navigateTo))
    }

    override fun onFailure(exception: Exception) {
        exception.printStackTrace()
        pkceNavigateTo = null
    }
}