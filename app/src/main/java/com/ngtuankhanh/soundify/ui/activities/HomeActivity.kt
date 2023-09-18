package com.ngtuankhanh.soundify.ui.activities

import android.net.Uri
import android.os.Bundle
import androidx.navigation.findNavController
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.R
import com.ngtuankhanh.soundify.databinding.ActivityHomeBinding
import com.ngtuankhanh.soundify.ui.models.TrackItem
import com.ngtuankhanh.soundify.ui.musicplayer.PlayerState

class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    var playerState = PlayerState.PAUSED
    lateinit var player: ExoPlayer
    var currentTrack: TrackItem ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideStreamingBar()

        player = ExoPlayer.Builder(this).build()

        binding.streamingButton.setOnClickListener {
            playerState = if (playerState == PlayerState.PAUSED) {
                player.play()
                binding.streamingButton.setImageResource(R.drawable.pause_circle_fill)
                PlayerState.PLAYING
            } else {
                player.pause()
                binding.streamingButton.setImageResource(R.drawable.play_circle_fill)
                PlayerState.PAUSED
            }
        }

        binding.streamingBar.setOnClickListener {
            if (currentTrack == null) {
                Toast.makeText(this,"No song stream right now!", Toast.LENGTH_SHORT).show()
            } else {
                val deepLinkUri = Uri.parse("soundify://musicplayer")
                findNavController(R.id.nav_host_fragment).navigate(deepLinkUri)
            }
        }

        hideNavigationBar()

        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        hideNavigationBar()
    }

    private fun hideNavigationBar() {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }

    fun changeCurrentTrack(track: TrackItem) {
        if (binding.streamingBar.visibility == View.GONE)
            showStreamingBar()
        Glide.with(binding.root)
            .load(track.imageUrl)
            .into(binding.streamingSongImage)
        currentTrack = track
        binding.streamingSongName.text = track.name
        binding.streamingSongArtist.text = track.artists.joinToString(", ")
        val mediaItem =
            MediaItem.fromUri("https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3")
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
        playerState = PlayerState.PLAYING
        binding.streamingButton.setImageResource(R.drawable.pause_circle_fill)
    }

    // Function to show the streaming bar
    fun showStreamingBar() {
        binding.streamingBar.visibility = View.VISIBLE
    }

    // Function to hide the streaming bar
    fun hideStreamingBar() {
        binding.streamingBar.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
