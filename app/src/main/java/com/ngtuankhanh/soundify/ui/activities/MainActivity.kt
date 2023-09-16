package com.ngtuankhanh.soundify.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adamratzman.spotify.auth.pkce.startSpotifyClientPkceLoginActivity
import com.ngtuankhanh.soundify.auth.SpotifyPkceLoginActivityImpl
import com.ngtuankhanh.soundify.ui.Model

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Model.credentialStore.spotifyToken != null) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        else {
            startSpotifyClientPkceLoginActivity(SpotifyPkceLoginActivityImpl::class.java)
            finish()
        }
    }
}