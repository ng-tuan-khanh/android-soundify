package com.ngtuankhanh.soundify.ui.collectioninfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.databinding.FragmentCollectionDetailBinding
import com.ngtuankhanh.soundify.ui.activities.HomeActivity
import com.ngtuankhanh.soundify.ui.adapters.TrackListAdapter
import com.ngtuankhanh.soundify.ui.models.ItemType
import com.ngtuankhanh.soundify.ui.models.TrackItem
import com.ngtuankhanh.soundify.utils.toast
import kotlinx.coroutines.launch

class CollectionDetailFragment : Fragment() {
    private lateinit var binding: FragmentCollectionDetailBinding
    private lateinit var viewModel: CollectionDetailViewModel
    private val trackListAdapter by lazy {
        TrackListAdapter(
            onPlayButtonClick = { trackItem: TrackItem ->
                (requireActivity() as HomeActivity).changeCurrentTrack(trackItem)
                Toast.makeText(
                    context,
                    "Playing track ${trackItem.name}",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onTrackClick = { trackItem: TrackItem ->
                (requireActivity() as HomeActivity).changeCurrentTrack(trackItem)
                val action =
                    CollectionDetailFragmentDirections.actionPlaylistDetailFragmentToMusicPlayerFragment()
                findNavController().navigate(action)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCollectionDetailBinding.inflate(inflater, container, false)

        // Set up the view model
        val viewModelFactory = CollectionDetailViewModel.Factory(requireActivity() as HomeActivity)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CollectionDetailViewModel::class.java)

        // Set up the recycler view
        binding.trackRecyclerView.apply {
            layoutManager = object : LinearLayoutManager(context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = trackListAdapter
        }

        // Set up the up button
        binding.upButton.setOnClickListener {
            this.findNavController().navigateUp()
        }

        // Argument null checks
        val collectionId = arguments?.getString("collectionId")
        val type = arguments?.get("type") as ItemType

        if (collectionId == null || type == null) {
            this.findNavController().navigateUp()
            toast(this.requireContext(), "Error requesting data. Returning to previous screen.")
            return binding.root
        }

        // Observing the StateFlow using Coroutine
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.imageUrl.collect {
                Glide.with(this@CollectionDetailFragment)
                    .load(it)
                    .into(binding.artworkImage)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.collectionName.collect {
                binding.collectionNameText.text = it
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            if (type == ItemType.Playlist) {
                viewModel.getPlaylistTracks(collectionId)
                viewModel.tracks.collect {
                    trackListAdapter.submitList(it)
                }
            } else if (type == ItemType.Album) {
                viewModel.getAlbumTracks(collectionId)
                viewModel.tracks.collect {
                    trackListAdapter.submitList(it)
                }
            }
        }

        return binding.root
    }
}
