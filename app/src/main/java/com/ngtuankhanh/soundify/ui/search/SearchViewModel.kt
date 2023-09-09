package com.ngtuankhanh.soundify.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngtuankhanh.soundify.data.models.SearchResult
import com.ngtuankhanh.soundify.data.repositories.FeaturedPlaylistsRepository
import com.ngtuankhanh.soundify.data.repositories.SearchItemsRepository
import com.ngtuankhanh.soundify.ui.models.DisplayAlbum
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _repository = SearchItemsRepository()
    private var _queryJob: Job = Job()

    private val _searchResult = MutableStateFlow(SearchResult())
    val searchResult: StateFlow<SearchResult> = _searchResult
    fun searchForItems(query: String) {
        _queryJob.cancel()
        _queryJob = viewModelScope.launch {
            _repository.searchForItems(query).collect {
                _searchResult.value = it
            }
        }
    }
    class Factory() : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                return SearchViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}