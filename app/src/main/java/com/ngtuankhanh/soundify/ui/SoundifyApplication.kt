package com.ngtuankhanh.soundify.ui

import android.app.Application
import android.content.Context
import com.adamratzman.spotify.auth.SpotifyDefaultCredentialStore
import com.ngtuankhanh.soundify.BuildConfig

class SoundifyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }

    companion object {
        lateinit var context: Context
    }
}

object SpotifyApi {
    val credentialStore by lazy {
        SpotifyDefaultCredentialStore(
            clientId = BuildConfig.SPOTIFY_CLIENT_ID,
            redirectUri = BuildConfig.SPOTIFY_REDIRECT_URI_PKCE,
            applicationContext = SoundifyApplication.context
        )
    }
}