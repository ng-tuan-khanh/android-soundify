package com.ngtuankhanh.soundify.ui.artistprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngtuankhanh.soundify.data.repositories.ArtistInfoRepository
import com.ngtuankhanh.soundify.ui.activities.BaseActivity
import com.ngtuankhanh.soundify.ui.models.CollectionItem
import com.ngtuankhanh.soundify.ui.models.ItemType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.ngtuankhanh.soundify.ui.models.TrackItem
import kotlinx.coroutines.launch

class ArtistProfileViewModel(activity: BaseActivity?) : ViewModel() {
    private val _repository = ArtistInfoRepository(activity)

    private val _imageUrl = MutableStateFlow("")
    val imageUrl: StateFlow<String>
        get() = _imageUrl

    private val _artistName = MutableStateFlow("")
    val artistName: StateFlow<String>
        get() = _artistName

    private val _tracks = MutableStateFlow<List<TrackItem>>(emptyList())
    val tracks: StateFlow<List<TrackItem>>
        get() = _tracks

    private val _albums = MutableStateFlow<List<CollectionItem>>(emptyList())
    val albums: StateFlow<List<CollectionItem>>
        get() = _albums

    fun getArtistInfo(id: String) {
        viewModelScope.launch {
            _repository.getArtistInfo(id).collect {
                it.let {
                    _imageUrl.value = it.images[0].url
                    _artistName.value = it.name
                }
            }
        }
    }

    fun getTopTracks(id: String) {
        viewModelScope.launch {
            _repository.getArtistTopTracks(id).collect {
                val res = mutableListOf<TrackItem>()
                it.forEach { track ->
                    res.add(
                        TrackItem(
                            track.id,
                            track.name,
                            track.artists.map { it.name },
                            track.album.images[0].url,
                            track.previewUrl ?: ""
                        )
                    )
                }
                _tracks.value = res
            }
        }
    }

    fun getAlbums(id: String) {
        viewModelScope.launch {
            _repository.getArtistAlbums(id).collect {
                val res = mutableListOf<CollectionItem>()
                it.forEach { album ->
                    res.add(
                        CollectionItem(
                            id = album.id,
                            name = album.name,
                            imageUrl = album.images[0].url,
                            totalTracks = album.totalTracks ?: 0,
                            type = ItemType.Album
                        )
                    )
                }
                _albums.value = res
            }
        }
    }

    class Factory(private val activity: BaseActivity?) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtistProfileViewModel::class.java)) {
                return ArtistProfileViewModel(activity) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
