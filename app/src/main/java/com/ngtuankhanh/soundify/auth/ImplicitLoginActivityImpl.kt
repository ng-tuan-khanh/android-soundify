package com.ngtuankhanh.soundify.auth

import android.content.Intent
import com.adamratzman.spotify.SpotifyImplicitGrantApi
import com.adamratzman.spotify.SpotifyScope
import com.adamratzman.spotify.auth.implicit.AbstractSpotifyAppImplicitLoginActivity
import com.ngtuankhanh.soundify.BuildConfig
import com.ngtuankhanh.soundify.ui.activities.MainActivity
import com.ngtuankhanh.soundify.ui.SoundifyApplication

class ImplicitLoginActivityImpl : AbstractSpotifyAppImplicitLoginActivity() {
    override val state: Int = 1337
    override val clientId: String = BuildConfig.SPOTIFY_CLIENT_ID
    override val redirectUri: String = BuildConfig.SPOTIFY_REDIRECT_URI_AUTH
    override val useDefaultRedirectHandler: Boolean = false
    override fun getRequestingScopes(): List<SpotifyScope> = SpotifyScope.values().toList()

    override fun onSuccess(spotifyApi: SpotifyImplicitGrantApi) {
        val credentialStore = (application as SoundifyApplication).credentialStore
        credentialStore.setSpotifyApi(spotifyApi)
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onFailure(errorMessage: String) {

    }
}