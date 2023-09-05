package com.ngtuankhanh.soundify.data.repositories

import com.adamratzman.spotify.models.SpotifySearchResult
import com.adamratzman.spotify.utils.Market
import com.ngtuankhanh.soundify.auth.guardValidSpotifyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchItemsRepository() {
    // Data source
    fun searchForItems(query: String): Flow<SpotifySearchResult> = flow {
        guardValidSpotifyApi { api ->
            emit(api.search.searchAllTypes(query = query, market = Market.VN))
        }
    }.flowOn(Dispatchers.IO)
}