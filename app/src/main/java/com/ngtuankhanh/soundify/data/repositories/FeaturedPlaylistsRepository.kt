package com.ngtuankhanh.soundify.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FeaturedPlaylistsRepository {
    suspend fun refreshPlaylists() {
        withContext(Dispatchers.IO) {

        }
    }
}