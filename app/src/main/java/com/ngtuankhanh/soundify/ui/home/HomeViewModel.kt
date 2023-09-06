package com.ngtuankhanh.soundify.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngtuankhanh.soundify.data.repositories.FeaturedPlaylistsRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val repository = FeaturedPlaylistsRepository()

    init {
        viewModelScope.launch {
            repository.getFeaturedPlaylists()
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