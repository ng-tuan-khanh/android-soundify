package com.ngtuankhanh.soundify.ui.home

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ngtuankhanh.soundify.ui.adapters.TopPlaylistsAdapter
import com.ngtuankhanh.soundify.ui.adapters.FeaturedPlaylistsAdapter
import com.ngtuankhanh.soundify.databinding.FragmentHomeBinding
import com.ngtuankhanh.soundify.ui.activities.HomeActivity
import com.ngtuankhanh.soundify.ui.models.ItemType
import kotlinx.coroutines.launch
import java.time.LocalTime

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val topPlaylistsAdapter by lazy {
        TopPlaylistsAdapter { playlist ->
            val action = HomeFragmentDirections.actionHomeFragmentToPlaylistDetailFragment(
                collectionId = playlist.id,
                type = ItemType.Playlist
            )
            this.findNavController().navigate(action)
        }
    }

    private val featuredPlaylistsAdapter by lazy {
        FeaturedPlaylistsAdapter(
            onPlaylistClick = { playlist ->
                val action = HomeFragmentDirections.actionHomeFragmentToPlaylistDetailFragment(
                    collectionId = playlist.id,
                    type = ItemType.Playlist
                )
                this.findNavController().navigate(action)
            },
            onPlayClick = {
                // Thêm logic để phát nhạc vào đây
            }
        )
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Setup HomeViewModel
        val homeViewModelFactory = HomeViewModel.Factory(requireActivity() as HomeActivity)
        val homeViewModel =
            ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)

        // Setup greeting text
        val currentTime = LocalTime.now().hour
        binding.greetingText.text = when (currentTime) {
            in 0..11 -> "Good morning"
            in 12..17 -> "Good afternoon"
            else -> "Good evening"
        }

        // Setup top playlists RecyclerView
        binding.topPlaylistsRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.topPlaylistsRecyclerView.adapter = topPlaylistsAdapter

        // Setup featured playlists RecyclerView
        binding.featuredPlaylistsRecyclerView.layoutManager = object :
            GridLayoutManager(context, 1) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.featuredPlaylistsRecyclerView.adapter = featuredPlaylistsAdapter

        // Observe data from HomeViewModel
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.featuredPlaylists.collect {
                    topPlaylistsAdapter.submitList(
                        if (it.size >= 6) it.subList(
                            0,
                            6
                        ) else it.subList(0, it.size)
                    )
                    featuredPlaylistsAdapter.submitList(
                        if (it.size >= 6) it.subList(
                            6,
                            it.size
                        ) else emptyList()
                    )
                }
            }
        }

        return binding.root
    }
}
