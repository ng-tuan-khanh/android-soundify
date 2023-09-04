package com.ngtuankhanh.soundify.musicplayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MusicPlayerViewModel: ViewModel() {
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