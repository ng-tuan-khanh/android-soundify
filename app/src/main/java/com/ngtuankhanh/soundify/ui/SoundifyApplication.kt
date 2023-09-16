package com.ngtuankhanh.soundify.ui

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.adamratzman.spotify.auth.SpotifyDefaultCredentialStore
import com.ngtuankhanh.soundify.BuildConfig

class SoundifyApplication : Application() {
    lateinit var model: Model

    override fun onCreate() {
        super.onCreate()
        model = Model
            context = applicationContext
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

    companion object {
        lateinit var context: Context
    }
}

object Model {
    val credentialStore by lazy {
        SpotifyDefaultCredentialStore(
            clientId = BuildConfig.SPOTIFY_CLIENT_ID,
            redirectUri = BuildConfig.SPOTIFY_REDIRECT_URI_PKCE,
            applicationContext = SoundifyApplication.context
        )
    }
}