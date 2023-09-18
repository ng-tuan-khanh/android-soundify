package com.ngtuankhanh.soundify.ui.activities

import android.net.Uri
import android.os.Bundle
import androidx.navigation.findNavController
import android.view.View
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.R
import com.ngtuankhanh.soundify.databinding.ActivityHomeBinding
import com.ngtuankhanh.soundify.ui.models.TrackItem
import com.ngtuankhanh.soundify.ui.musicplayer.PlayerState
import com.ngtuankhanh.soundify.utils.toast

class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    var playerState = PlayerState.PAUSED
    lateinit var player: ExoPlayer
    var currentTrack: TrackItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideStreamingBar()

        player = ExoPlayer.Builder(this).build()

        binding.streamingButton.setOnClickListener {
            playerState = if (playerState == PlayerState.PAUSED) {
                if (currentTrack?.previewUrl == "") {
                    toast("Track not available")
                    return@setOnClickListener
                }
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
                toast("No track is playing")
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

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun hideNavigationBar() {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }

    fun changeCurrentTrack(track: TrackItem) {
        if (binding.streamingBar.visibility == View.GONE) showStreamingBar()

        Glide.with(binding.root)
            .load(track.imageUrl)
            .into(binding.streamingSongImage)

        currentTrack = track
        binding.streamingSongName.text = track.name
        binding.streamingSongArtist.text = track.artists.joinToString(", ")
        binding.streamingButton.setImageResource(R.drawable.pause_circle_fill)

        if (currentTrack?.previewUrl == "") {
            toast("Track not available")
            return
        }

        val mediaItem = MediaItem.fromUri(track.previewUrl)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
        playerState = PlayerState.PLAYING
    }

    fun showStreamingBar() {
        binding.streamingBar.visibility = View.VISIBLE
    }

    fun hideStreamingBar() {
        binding.streamingBar.visibility = View.GONE
    }
}
