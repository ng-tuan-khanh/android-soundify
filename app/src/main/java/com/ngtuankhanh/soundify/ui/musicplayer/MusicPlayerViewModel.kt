package com.ngtuankhanh.soundify.ui.musicplayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngtuankhanh.soundify.data.repositories.FeaturedPlaylistsRepository
import kotlinx.coroutines.launch

class MusicPlayerViewModel: ViewModel() {
    val repo = FeaturedPlaylistsRepository()
    init {
        viewModelScope.launch {
            repo.getFeaturedPlaylists().collect {

            }
        }
    }
    class Factory() : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MusicPlayerViewModel::class.java)) {
                return MusicPlayerViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}