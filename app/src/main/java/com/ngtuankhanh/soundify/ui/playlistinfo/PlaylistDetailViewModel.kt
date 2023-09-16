package com.ngtuankhanh.soundify.ui.playlistdetail

import androidx.lifecycle.ViewModel
import com.ngtuankhanh.soundify.ui.models.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.ngtuankhanh.soundify.ui.models.Track

class PlaylistDetailViewModel : ViewModel() {

    // MutableStateFlow to update the tracks from inside the ViewModel
    private val _tracks = MutableStateFlow<List<Track>>(emptyList())

    // Exposed read-only StateFlow
    val tracks: StateFlow<List<Track>> get() = _tracks

    init {
        fetchPlaylistDetails()
        fetchTracks()
    }
    private fun fetchPlaylistDetails() {
        // Simulating fetching playlist details
        // Example:
        // your logic here
    }

    private fun fetchTracks() {
        // Simulating fetching tracks for a playlist
        // Example:
        // your logic here

        // For now, we use dummy tracks for demonstration
        val dummyTracks = mutableListOf<Track>()
        for (i in 1..10) {
            val track = Track().apply {
                id = i.toString()
                name = "Track $i"
                backgroundImage = Image().apply {
                    url = "Your track image URL here"
                }
            }
            dummyTracks.add(track)
        }
        _tracks.value = dummyTracks
    }
}
