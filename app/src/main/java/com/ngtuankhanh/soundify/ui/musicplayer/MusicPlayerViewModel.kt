package com.ngtuankhanh.soundify.ui.musicplayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngtuankhanh.soundify.data.repositories.FeaturedPlaylistsRepository
import com.ngtuankhanh.soundify.ui.activities.BaseActivity
import kotlinx.coroutines.launch

class MusicPlayerViewModel(activity: BaseActivity?): ViewModel() {
    private val _repository = FeaturedPlaylistsRepository(activity)
    init {
        viewModelScope.launch {
            _repository.getFeaturedPlaylists().collect {

            }
        }
    }
    class Factory(private val activity: BaseActivity?) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MusicPlayerViewModel::class.java)) {
                return MusicPlayerViewModel(activity) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}