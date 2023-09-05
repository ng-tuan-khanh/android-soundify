package com.ngtuankhanh.soundify.musicplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ngtuankhanh.soundify.R
import com.ngtuankhanh.soundify.databinding.FragmentMusicPlayerBinding

class MusicPlayerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMusicPlayerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_music_player, container, false
        )

        val musicPlayerViewModelFactory = MusicPlayerViewModel.Factory()
        val musicPlayerViewModel =
            ViewModelProvider(this, musicPlayerViewModelFactory).get(MusicPlayerViewModel::class.java)

        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = musicPlayerViewModel

        return binding.root
    }
}




