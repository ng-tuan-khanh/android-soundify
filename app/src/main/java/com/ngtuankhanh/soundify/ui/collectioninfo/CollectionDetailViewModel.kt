package com.ngtuankhanh.soundify.ui.collectioninfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngtuankhanh.soundify.data.repositories.CollectionInfoRepository
import com.ngtuankhanh.soundify.ui.activities.BaseActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.ngtuankhanh.soundify.ui.models.TrackItem
import kotlinx.coroutines.launch

class CollectionDetailViewModel(activity: BaseActivity?) : ViewModel() {
    private val _repository = CollectionInfoRepository(activity)

    private val _tracks = MutableStateFlow<List<TrackItem>>(emptyList())
    val tracks: StateFlow<List<TrackItem>>
        get() = _tracks

    private val _imageUrl = MutableStateFlow("")
    val imageUrl: StateFlow<String>
        get() = _imageUrl

    private val _collectionName = MutableStateFlow("")
    val collectionName: StateFlow<String>
        get() = _collectionName

    fun getPlaylistTracks(id: String) {
        viewModelScope.launch {
            _repository.getPlaylist(id).collect {
                val res = mutableListOf<TrackItem>()
                it?.let {
                    _imageUrl.value = it.images[0].url
                    _collectionName.value = it.name
                    it.tracks.forEach { playable ->
                        playable?.asTrack?.let { track ->
                            res.add(
                                TrackItem(
                                    track.id,
                                    track.name,
                                    track.artists.map { it.name },
                                    track.album.images[0].url
                                )
                            )
                        }
                    }
                }
                _tracks.value = res
            }
        }
    }

    fun getAlbumTracks(id: String) {
        viewModelScope.launch {
            _repository.getAlbum(id).collect {
                val res = mutableListOf<TrackItem>()
                it?.let {
                    _imageUrl.value = it.images[0].url
                    _collectionName.value = it.name
                    it.tracks.forEach { track ->
                        track?.let {
                            res.add(
                                TrackItem(
                                    it.id,
                                    it.name,
                                    it.artists.map { it.name },
                                    it.album.images[0].url
                                )
                            )
                        }
                    }
                }
                _tracks.value = res
            }
        }
    }

    class Factory(private val activity: BaseActivity?) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CollectionDetailViewModel::class.java)) {
                return CollectionDetailViewModel(activity) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
