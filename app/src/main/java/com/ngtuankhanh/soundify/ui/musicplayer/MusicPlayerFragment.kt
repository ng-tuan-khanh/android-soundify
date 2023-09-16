package com.ngtuankhanh.soundify.ui.musicplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.ngtuankhanh.soundify.R
import com.ngtuankhanh.soundify.databinding.FragmentMusicPlayerBinding
import com.ngtuankhanh.soundify.ui.activities.BaseActivity
import com.ngtuankhanh.soundify.ui.activities.HomeActivity


enum class PlayerState {
    PAUSED, PLAYING
}

class MusicPlayerFragment : Fragment() {
    private lateinit var binding: FragmentMusicPlayerBinding
    private lateinit var player: ExoPlayer
    private var playerState = PlayerState.PAUSED
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_music_player, container, false
        )

        val musicPlayerViewModelFactory = MusicPlayerViewModel.Factory(requireActivity() as HomeActivity)
        val musicPlayerViewModel =
            ViewModelProvider(
                this,
                musicPlayerViewModelFactory
            ).get(MusicPlayerViewModel::class.java)

        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = musicPlayerViewModel

        player = ExoPlayer.Builder(requireContext()).build()

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

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}




