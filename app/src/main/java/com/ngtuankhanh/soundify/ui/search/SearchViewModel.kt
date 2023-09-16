package com.ngtuankhanh.soundify.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngtuankhanh.soundify.data.models.SearchResults
import com.ngtuankhanh.soundify.data.repositories.SearchItemsRepository
import com.ngtuankhanh.soundify.ui.activities.BaseActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(activity: BaseActivity?) : ViewModel() {
    private val _repository = SearchItemsRepository(activity)
    private var _queryJob: Job = Job()

    private val _searchResults = MutableStateFlow(SearchResults())
    val searchResults: StateFlow<SearchResults> = _searchResults
    fun searchForItems(query: String) {
        _queryJob.cancel()
        _queryJob = viewModelScope.launch {
            _repository.searchForItems(query).collect {
                _searchResults.value = it
            }
        }
    }
    class Factory(private val activity: BaseActivity?) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                return SearchViewModel(activity) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}