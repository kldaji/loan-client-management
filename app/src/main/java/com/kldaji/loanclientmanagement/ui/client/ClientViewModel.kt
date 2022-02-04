package com.kldaji.loanclientmanagement.ui.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kldaji.loanclientmanagement.model.data.Client
import com.kldaji.loanclientmanagement.model.data.RecentSearchWord
import com.kldaji.loanclientmanagement.model.local.client.ClientLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(private val clientLocalDataSource: ClientLocalDataSource) :
    ViewModel() {
    private val clientMutableList = MutableLiveData<List<Client>>()
    val clientList: LiveData<List<Client>> = clientMutableList
    private val recentSearchWordMutableList = MutableLiveData<List<RecentSearchWord>>()
    val recentSearchWordList: LiveData<List<RecentSearchWord>> = recentSearchWordMutableList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            clientMutableList.postValue(clientLocalDataSource.getAllClients())
        }
        // dummy data
        recentSearchWordMutableList.value = listOf(RecentSearchWord(1, "123"),
            RecentSearchWord(1, "123"),
            RecentSearchWord(1, "123"))
    }
}
