package com.ngtuankhanh.soundify.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.adamratzman.spotify.auth.pkce.startSpotifyClientPkceLoginActivity
import com.ngtuankhanh.soundify.auth.PkceLoginActivityImpl
import com.ngtuankhanh.soundify.databinding.ActivityLogInBinding

class LogInActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logInButton.setOnClickListener {
            startSpotifyClientPkceLoginActivity(PkceLoginActivityImpl::class.java)
        }
    }
}