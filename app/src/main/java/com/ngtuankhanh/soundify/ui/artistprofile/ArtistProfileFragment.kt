package com.ngtuankhanh.soundify.ui.artistprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.databinding.FragmentArtistProfileBinding
import com.ngtuankhanh.soundify.ui.activities.HomeActivity
import com.ngtuankhanh.soundify.ui.adapters.TopPlaylistsAdapter
import com.ngtuankhanh.soundify.ui.adapters.TrackListAdapter
import com.ngtuankhanh.soundify.utils.toast
import kotlinx.coroutines.launch

class ArtistProfileFragment : Fragment() {
    private lateinit var binding: FragmentArtistProfileBinding

    private val topPlaylistsAdapter by lazy { TopPlaylistsAdapter {} }
    private val trackListAdapter by lazy { TrackListAdapter {} }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArtistProfileBinding.inflate(inflater, container, false)

        // Set up the view model
        val viewModelFactory = ArtistProfileViewModel.Factory(requireActivity() as HomeActivity)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(ArtistProfileViewModel::class.java)

        // Set up the up button
        binding.upButton.setOnClickListener {
            this.findNavController().navigateUp()
        }

        // Set up the recycler views
        binding.albumsRecyclerView.apply {
            layoutManager = object: LinearLayoutManager(context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = topPlaylistsAdapter
        }

        binding.tracksRecyclerView.apply {
            layoutManager = object: LinearLayoutManager(context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = trackListAdapter
        }

        val artistId = arguments?.getString("artistId")
        if (artistId == null) {
            this.findNavController().navigateUp()
            toast(this.requireContext(), "Error requesting data. Returning to previous screen.")
            return binding.root
        }

        viewModel.getArtistInfo(artistId)

        // Observe data changes
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.imageUrl.collect {
                Glide.with(this@ArtistProfileFragment)
                    .load(it)
                    .into(binding.artistImage)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.artistName.collect {
                binding.artistNameText.text = it
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getAlbums(artistId)
            viewModel.albums.collect {
                topPlaylistsAdapter.submitList(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getTopTracks(artistId)
            viewModel.tracks.collect {
                trackListAdapter.submitList(it)
            }
        }

        return binding.root
    }
}
