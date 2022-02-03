package com.kldaji.loanclientmanagement.ui.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kldaji.loanclientmanagement.model.data.Client
import com.kldaji.loanclientmanagement.model.data.RecentSearchWord
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor() : ViewModel() {
    private val clientMutableList = MutableLiveData<List<Client>>()
    val clientList: LiveData<List<Client>> = clientMutableList
    private val recentSearchWordMutableList = MutableLiveData<List<RecentSearchWord>>()
    val recentSearchWordList: LiveData<List<RecentSearchWord>> = recentSearchWordMutableList

    init {
        // dummy data
        clientMutableList.value =
            listOf(Client(1, "123"), Client(1, "123"), Client(1, "123"), Client(1, "123"))
        recentSearchWordMutableList.value = listOf(RecentSearchWord(1, "123"),
            RecentSearchWord(1, "123"),
            RecentSearchWord(1, "123"))
    }
}
