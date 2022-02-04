package com.kldaji.loanclientmanagement.ui.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kldaji.loanclientmanagement.model.data.RecentSearchWord
import com.kldaji.loanclientmanagement.model.local.recentSearchWord.RecentSearchWordLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentSearchWordViewModel @Inject constructor(private val recentSearchWordLocalDataSource: RecentSearchWordLocalDataSource) :
    ViewModel() {
    private val _recentSearchWordList = MutableLiveData<List<RecentSearchWord>>()
    val recentSearchWordList: LiveData<List<RecentSearchWord>> = _recentSearchWordList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _recentSearchWordList.value = recentSearchWordLocalDataSource.getAllRecentSearchWords()
        }
    }
}
