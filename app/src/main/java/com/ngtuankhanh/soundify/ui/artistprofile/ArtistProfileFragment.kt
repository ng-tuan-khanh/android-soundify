package com.ngtuankhanh.soundify.ui.artistprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ngtuankhanh.soundify.databinding.FragmentArtistProfileBinding
import com.ngtuankhanh.soundify.ui.adapters.FavoritePlaylistAdapter
import com.ngtuankhanh.soundify.ui.adapters.TrackListAdapter
import com.ngtuankhanh.soundify.ui.models.Artist
import com.ngtuankhanh.soundify.ui.models.PlaylistIcon
import com.ngtuankhanh.soundify.ui.models.Track

class ArtistProfileFragment : Fragment() {
    private lateinit var binding: FragmentArtistProfileBinding

    // Giả lập việc tạo Adapters
    private val favoritePlaylistAdapter by lazy { FavoritePlaylistAdapter {} }
    private val trackListAdapter by lazy { TrackListAdapter {} }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArtistProfileBinding.inflate(inflater, container, false)
        //val artistId = arguments?.getString("artistId")

        // Sử dụng artistId để lấy thông tin và hiển thị - ở đây tôi sẽ giả lập việc này
        val dummyArtist = getDummyArtist()
        displayArtistInfo(dummyArtist)

        return binding.root
    }

    private fun getDummyArtist(): Artist {
        val artist = Artist()
        artist.name = "Artist Name"

        // Tạo Track giả mạo
        val dummyTrack1 = Track().apply {
            name = "Dummy Track 1"
            // Set các trường khác của Track nếu cần
        }
        val dummyTrack2 = Track().apply {
            name = "Dummy Track 2"
            // Set các trường khác của Track nếu cần
        }

        artist.listOfTracks = listOf(dummyTrack1, dummyTrack2)

        // Tạo PlaylistIcon giả mạo
        val dummyPlaylistIcon1 = PlaylistIcon().apply {
            name = "Dummy Playlist 1"
            // Set các trường khác của PlaylistIcon nếu cần, ví dụ như backgroundImage
        }
        val dummyPlaylistIcon2 = PlaylistIcon().apply {
            name = "Dummy Playlist 2"
            // Set các trường khác của PlaylistIcon nếu cần
        }

        artist.listOfAlbums = listOf(dummyPlaylistIcon1, dummyPlaylistIcon2)

        return artist
    }


    private fun displayArtistInfo(artist: Artist) {
        binding.artistFragmentArtistName.text = artist.name
        // Cần thiết lập ảnh cho avatar và background ở đây nếu bạn muốn

        binding.artistFragmentAlbumsRecyclerView.apply {
            adapter = favoritePlaylistAdapter
            // Cập nhật dữ liệu cho adapter
            favoritePlaylistAdapter.submitList(artist.listOfAlbums)
        }

        binding.artistFragmentTracksRecyclerView.apply {
            adapter = trackListAdapter
            // Cập nhật dữ liệu cho adapter
            trackListAdapter.submitList(artist.listOfTracks)
        }
    }
}
