package com.ngtuankhanh.soundify.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ngtuankhanh.soundify.ui.adapters.FavoritePlaylistAdapter
import com.ngtuankhanh.soundify.ui.adapters.SeriAlbumAdapter
import com.ngtuankhanh.soundify.databinding.FragmentHomeBinding
import com.ngtuankhanh.soundify.ui.models.Image
import com.ngtuankhanh.soundify.ui.models.PlaylistIcon
import com.ngtuankhanh.soundify.data.models.DisplayAlbum

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val favoriteAdapter by lazy { FavoritePlaylistAdapter() }
    private val albumAdapter by lazy { SeriAlbumAdapter(generateDummyDisplayAlbums()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Setup favorite playlist RecyclerView
        binding.homeFragmentFavoriteGrid.layoutManager = GridLayoutManager(context, 2)
        binding.homeFragmentFavoriteGrid.adapter = favoriteAdapter
        favoriteAdapter.submitList(generateDummyPlaylists())

        // Setup album series RecyclerView
        binding.homeFragmentAlbumSeriesRecyclerView.layoutManager = GridLayoutManager(context, 1)  // or any other desired layout manager
        binding.homeFragmentAlbumSeriesRecyclerView.adapter = albumAdapter

        return binding.root
    }

    val imageURL: String = "data:image/png;base64,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" // Replace with your desired image URL or keep it as an empty string
    private fun generateDummyDisplayAlbums(): List<DisplayAlbum> {
        val list = mutableListOf<DisplayAlbum>()
        for (i in 1..6) { // Assuming you want 6 dummy albums, adjust as needed
            val album = DisplayAlbum().apply {
                ID = i.toString()
                Name = "Album $i"
                ArtistName = "Artist $i"
                ImageURL = imageURL
                TotalTracks = (10..15).random() // Random number of tracks between 10 to 15
            }
            list.add(album)
        }
        return list
    }

    private fun generateDummyPlaylists(): List<PlaylistIcon> {
        val list = mutableListOf<PlaylistIcon>()
        for (i in 1..6) {
            val playlist = PlaylistIcon().apply {
                name = "Playlist $i"
                id = i.toString()
                backgroundImage = Image().apply {
                    url = imageURL
                }
            }
            list.add(playlist)
        }
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
