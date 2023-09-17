package com.ngtuankhanh.soundify.ui.yourlibrary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngtuankhanh.soundify.data.repositories.YourLibraryItemsRepository
import com.ngtuankhanh.soundify.ui.activities.BaseActivity
import com.ngtuankhanh.soundify.ui.models.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class YourLibraryViewModel(activity: BaseActivity?) : ViewModel() {
    private val _repository = YourLibraryItemsRepository(activity)

    private val _yourLibraryItems = MutableStateFlow(emptyList<Item>())
    val yourLibraryItems: StateFlow<List<Item>>
        get() = _yourLibraryItems

    init {
        viewModelScope.launch {
            _repository.yourLibraryItems.collect {
                _yourLibraryItems.value = it.toUIModels()
            }
        }
    }
    class Factory(private val activity: BaseActivity?) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(YourLibraryViewModel::class.java)) {
                return YourLibraryViewModel(activity) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}