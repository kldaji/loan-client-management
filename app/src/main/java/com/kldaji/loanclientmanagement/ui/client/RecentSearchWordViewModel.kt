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

    private val _recentSearchWordInfoError = MutableLiveData<Boolean>()
    val recentSearchWordInfoError: LiveData<Boolean> = _recentSearchWordInfoError

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _recentSearchWordList.postValue(recentSearchWordLocalDataSource.getAllRecentSearchWords())
        }
    }

    fun addRecentSearchWord(newRecentSearchWord: RecentSearchWord) {
        checkRecentSearchWordInfo(newRecentSearchWord) ?: return
        viewModelScope.launch(Dispatchers.IO) {
            recentSearchWordLocalDataSource.insertRecentSearchWord(newRecentSearchWord)
        }
        val tempRecentSearchWordList =
            _recentSearchWordList.value?.toMutableList() ?: return
        tempRecentSearchWordList.add(0, newRecentSearchWord)
        _recentSearchWordList.value = tempRecentSearchWordList
    }

    private fun checkRecentSearchWordInfo(recentSearchWord: RecentSearchWord): Unit? {
        with(recentSearchWord) {
            if (word == "") {
                _recentSearchWordInfoError.value = true
                return null
            }
        }
        return Unit
    }

    fun doneRecentSearchWordInfoError() {
        _recentSearchWordInfoError.value = false
    }

    fun deleteRecentSearchWord(recentSearchWord: RecentSearchWord) {
        viewModelScope.launch(Dispatchers.IO) {
            recentSearchWordLocalDataSource.deleteRecentSearchWord(recentSearchWord)
        }
        val tempRecentSearchWordList =
            _recentSearchWordList.value?.toMutableList() ?: return
        tempRecentSearchWordList.remove(recentSearchWord)
        _recentSearchWordList.value = tempRecentSearchWordList
    }
}
