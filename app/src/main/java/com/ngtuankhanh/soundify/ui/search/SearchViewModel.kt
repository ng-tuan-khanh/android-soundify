package com.ngtuankhanh.soundify.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngtuankhanh.soundify.ui.models.SearchResults
import com.ngtuankhanh.soundify.data.repositories.SearchItemsRepository
import com.ngtuankhanh.soundify.ui.models.Image
import com.ngtuankhanh.soundify.ui.models.ItemType
import com.ngtuankhanh.soundify.ui.models.SearchItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _searchResults = MutableStateFlow(generateFakeItems(query = ""))
    val searchResults: StateFlow<List<SearchItem>> = _searchResults

    fun searchForItems(query: String) {
        viewModelScope.launch {
            delay(500) // Giả sử đang đợi kết quả từ máy chủ.
            _searchResults.value = generateFakeItems(query)
        }
    }

    private fun generateFakeItems(query: String): List<SearchItem> {
        val list = mutableListOf<SearchItem>()
        for (i in 1..10) {
            val item = SearchItem().apply {
                name = "Item $i: $query"
                id = i.toString()
                avatarImage = Image().apply { url = "https://fakeurl.com/$i.jpg" }
                type = when (i % 3) {
                    0 -> ItemType.PLAYLIST
                    1 -> ItemType.ARTIST
                    else -> ItemType.TRACK
                }
            }
            list.add(item)
        }
        return list
    }
}

//class SearchViewModel : ViewModel() {
//    private val _repository = SearchItemsRepository()
//    private var _queryJob: Job = Job()
//
//    private val _searchResults = MutableStateFlow(SearchResults())
//    val searchResults: StateFlow<SearchResults> = _searchResults
//    fun searchForItems(query: String) {
//        _queryJob.cancel()
//        _queryJob = viewModelScope.launch {
//            _repository.searchForItems(query).collect {
//                _searchResults.value = it
//            }
//        }
//    }
//    class Factory() : ViewModelProvider.Factory {
//        @Suppress("unchecked_cast")
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
//                return SearchViewModel() as T
//            }
//            throw IllegalArgumentException("Unknown ViewModel class")
//        }
//    }
//}