package com.ngtuankhanh.soundify.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngtuankhanh.soundify.R
import com.ngtuankhanh.soundify.data.repositories.FeaturedPlaylistsRepository
import com.ngtuankhanh.soundify.ui.activities.BaseActivity
import com.ngtuankhanh.soundify.ui.models.TrackCollection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(activity: BaseActivity?) : ViewModel() {
    private val _repository = FeaturedPlaylistsRepository(activity)
    private val _featuredPlaylists = MutableStateFlow(emptyList<TrackCollection>())
    val featuredPlaylists: StateFlow<List<TrackCollection>>
        get() = _featuredPlaylists

    init {
        viewModelScope.launch {
            _repository.getFeaturedPlaylists().collect { list ->
                _featuredPlaylists.value = list.map {
                    TrackCollection(
                        id = it.id,
                        name = it.name,
                        artistName = it.owner.displayName ?: "",
                        imageUrl = it.images[0]?.url ?: "",
                        totalTracks = it.tracks.size
                    )
                }
            }
        }
    }

    class Factory(private val activity: BaseActivity?) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(activity) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}