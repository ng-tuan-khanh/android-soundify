package com.ngtuankhanh.soundify.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngtuankhanh.soundify.data.repositories.FeaturedPlaylistsRepository
import com.ngtuankhanh.soundify.ui.models.DisplayAlbum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = FeaturedPlaylistsRepository()
    private val _featuredPlaylists = MutableStateFlow(emptyList<DisplayAlbum>())
    val featuredPlaylists: StateFlow<List<DisplayAlbum>>
        get() = _featuredPlaylists
    init {
        viewModelScope.launch {
            repository.getFeaturedPlaylists().collect { list ->
                _featuredPlaylists.value = list.map {
                    DisplayAlbum(id = it.id, name = it.name, artistName = it.owner.displayName, imageUrl = it.images[0]?.url)
                }
            }
        }
    }

    class Factory() : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}