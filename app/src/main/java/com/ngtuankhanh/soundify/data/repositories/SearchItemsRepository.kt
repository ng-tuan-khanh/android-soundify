package com.ngtuankhanh.soundify.data.repositories

import com.adamratzman.spotify.models.SpotifySearchResult
import com.adamratzman.spotify.utils.Market
import com.ngtuankhanh.soundify.auth.guardValidSpotifyApi
import com.ngtuankhanh.soundify.data.models.SearchResults
import com.ngtuankhanh.soundify.ui.activities.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class SearchItemsRepository(private val activity: BaseActivity?) {
    // Data source
    fun searchForItems(query: String): Flow<SearchResults> = flow {
        var res = SpotifySearchResult()
        activity?.guardValidSpotifyApi { api ->
            res = api.search.searchAllTypes(query = query, market = Market.VN)
        }
        emit(res)
    }.map { it ->
        SearchResults(
            albums = it.albums?.items ?: emptyList(),
            artists = it.artists?.items ?: emptyList(),
            playlists = it.playlists?.items ?: emptyList(),
            tracks = it.tracks?.items ?: emptyList()
        )
    }.flowOn(Dispatchers.IO)
}