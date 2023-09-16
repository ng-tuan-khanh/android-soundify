package com.ngtuankhanh.soundify.auth

import android.app.Activity
import android.content.Intent
import com.adamratzman.spotify.SpotifyClientApi
import com.adamratzman.spotify.SpotifyScope
import com.adamratzman.spotify.auth.pkce.AbstractSpotifyPkceLoginActivity
import com.ngtuankhanh.soundify.BuildConfig
import com.ngtuankhanh.soundify.ui.SoundifyApplication
import com.ngtuankhanh.soundify.ui.activities.HomeActivity
import com.ngtuankhanh.soundify.utils.toast

internal var pkceClassBackTo: Class<out Activity>? = null

class SpotifyPkceLoginActivityImpl : AbstractSpotifyPkceLoginActivity() {
    override val clientId = BuildConfig.SPOTIFY_CLIENT_ID
    override val redirectUri = BuildConfig.SPOTIFY_REDIRECT_URI_PKCE
    override val scopes = SpotifyScope.values().toList()

    override fun onSuccess(api: SpotifyClientApi) {
        val model = (application as SoundifyApplication).model
        model.credentialStore.setSpotifyApi(api)
        val classBackTo = pkceClassBackTo ?: HomeActivity::class.java
        pkceClassBackTo = null
        toast("Authentication via PKCE has completed. Launching ${classBackTo.simpleName}..")
        startActivity(Intent(this, classBackTo))
        finish()
    }

    override fun onFailure(exception: Exception) {
        exception.printStackTrace()
        pkceClassBackTo = null
        toast("Auth failed: ${exception.message}")
        finish()
    }
}