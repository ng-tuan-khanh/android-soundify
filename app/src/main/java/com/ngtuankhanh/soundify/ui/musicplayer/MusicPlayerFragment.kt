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
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.R
import com.ngtuankhanh.soundify.databinding.FragmentMusicPlayerBinding
import com.ngtuankhanh.soundify.ui.activities.BaseActivity
import com.ngtuankhanh.soundify.ui.activities.HomeActivity
import com.ngtuankhanh.soundify.ui.models.TrackItem


enum class PlayerState {
    PAUSED, PLAYING
}

class MusicPlayerFragment : Fragment() {
    private lateinit var binding: FragmentMusicPlayerBinding
    private lateinit var player: ExoPlayer
    private var playerState = PlayerState.PAUSED
    lateinit var track: TrackItem
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_music_player, container, false
        )

        (requireActivity() as HomeActivity).hideStreamingBar()

        val musicPlayerViewModelFactory = MusicPlayerViewModel.Factory(requireActivity() as HomeActivity)
        val musicPlayerViewModel =
            ViewModelProvider(
                this,
                musicPlayerViewModelFactory
            ).get(MusicPlayerViewModel::class.java)
        track = (requireActivity() as HomeActivity).currentTrack!!

        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = musicPlayerViewModel
        Glide.with(binding.root)
            .load(track.imageUrl)
            .into(binding.artworkImageView)

        binding.songNameTextView.text = track.name
        binding.artistTextView.text = track.artists.joinToString(", ")

        player = (requireActivity() as HomeActivity).player
        playerState = (requireActivity() as HomeActivity).playerState
        if (playerState == PlayerState.PLAYING) {
            binding.playButton.setImageResource(R.drawable.pause_circle_fill)
        } else {
            binding.playButton.setImageResource(R.drawable.play_circle_fill)
        }

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

        binding.upButton.setOnClickListener {
            (requireActivity() as HomeActivity).showStreamingBar()
            findNavController().navigateUp()
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




