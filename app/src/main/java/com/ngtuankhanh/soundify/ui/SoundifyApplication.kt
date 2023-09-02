package com.ngtuankhanh.soundify.ui

import android.app.Application
import com.adamratzman.spotify.auth.SpotifyDefaultCredentialStore
import com.ngtuankhanh.soundify.BuildConfig

class SoundifyApplication: Application() {
    val credentialStore by lazy {
        SpotifyDefaultCredentialStore(
            clientId = BuildConfig.SPOTIFY_CLIENT_ID,
            redirectUri = BuildConfig.SPOTIFY_REDIRECT_URI_PKCE,
            applicationContext = applicationContext
        )
    }
}