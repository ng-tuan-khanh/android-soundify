package com.ngtuankhanh.soundify.ui.playlistinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ngtuankhanh.soundify.databinding.FragmentPlaylistDetailBinding
import com.ngtuankhanh.soundify.ui.adapters.TrackListAdapter
import com.ngtuankhanh.soundify.ui.models.Track
import com.ngtuankhanh.soundify.ui.playlistdetail.PlaylistDetailViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlaylistDetailFragment : Fragment() {

    private lateinit var binding: FragmentPlaylistDetailBinding

    private val viewModel: PlaylistDetailViewModel by viewModels()
    private val trackListAdapter = TrackListAdapter({track: Track -> Toast.makeText(context, "Playing track", Toast.LENGTH_SHORT).show() })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaylistDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val playlistId = arguments?.getString("playlistId")
        setupRecyclerView()

        binding.fragmentPlaylistdetailBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        // Observing the StateFlow using Coroutine
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tracks.collect { trackList ->
                trackListAdapter.submitList(trackList)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.fragmentPlaylistdetailTrackRecylerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = trackListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Giải phóng binding để tránh rò rỉ bộ nhớ
        // Do binding chưa phải là nullable nên không cần thiết lập nó thành null
    }
}
