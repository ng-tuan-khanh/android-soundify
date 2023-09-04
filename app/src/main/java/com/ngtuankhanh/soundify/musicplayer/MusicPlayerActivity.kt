package com.ngtuankhanh.soundify.musicplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.ngtuankhanh.soundify.R
import com.ngtuankhanh.soundify.databinding.ActivityMusicPlayerBinding

// This activity is used to test the MusicPlayer only

enum class PlayerState {
    PLAYING,
    PAUSED
}

class MusicPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMusicPlayerBinding
    private lateinit var player: ExoPlayer
    private var playerState = PlayerState.PAUSED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMusicPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        player = ExoPlayer.Builder(this).build()

        val mediaItem =
            MediaItem.fromUri("https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3")
        player.setMediaItem(mediaItem)
        player.prepare()

        binding.playButton.setOnClickListener {
            playerState = if (playerState == PlayerState.PAUSED) {
                player.play()
                binding.playButton.setImageResource(R.drawable.pause_circle_fill)
                PlayerState.PLAYING
            } else {
                player.pause()
                binding.playButton.setImageResource(R.drawable.play_circle_fill)
                PlayerState.PAUSED
            }
        }

        binding.previousButton.setOnClickListener {
            player.seekToPreviousMediaItem()
        }

        binding.nextButton.setOnClickListener {
            player.seekToNextMediaItem()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}