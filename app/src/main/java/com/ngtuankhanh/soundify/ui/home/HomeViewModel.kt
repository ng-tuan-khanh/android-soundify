package com.ngtuankhanh.soundify.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngtuankhanh.soundify.data.repositories.FeaturedPlaylistsRepository
import com.ngtuankhanh.soundify.ui.activities.BaseActivity
import com.ngtuankhanh.soundify.ui.models.CollectionItem
import com.ngtuankhanh.soundify.ui.models.ItemType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(activity: BaseActivity?) : ViewModel() {
    private val _repository = FeaturedPlaylistsRepository(activity)
    private val _featuredPlaylists = MutableStateFlow(emptyList<CollectionItem>())
    val featuredPlaylists: StateFlow<List<CollectionItem>>
        get() = _featuredPlaylists

    init {
        viewModelScope.launch {
            _repository.getFeaturedPlaylists().collect { list ->
                _featuredPlaylists.value = list.map {
                    CollectionItem(
                        id = it.id,
                        name = it.name,
                        imageUrl = it.images[0]?.url ?: "",
                        totalTracks = it.tracks.size,
                        type = ItemType.Playlist
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