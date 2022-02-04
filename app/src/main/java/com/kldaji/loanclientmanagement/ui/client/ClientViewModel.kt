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

    private val _clientInfoError = MutableLiveData<Boolean>()
    val clientInfoError: LiveData<Boolean> = _clientInfoError
    private val _successInAddClient = MutableLiveData<Boolean>()
    val successInAddClient: LiveData<Boolean> = _successInAddClient

    init {
        viewModelScope.launch(Dispatchers.IO) {
            clientMutableList.postValue(clientLocalDataSource.getAllClients())
        }
        // dummy data
        recentSearchWordMutableList.value = listOf(RecentSearchWord(1, "123"),
            RecentSearchWord(1, "123"),
            RecentSearchWord(1, "123"))
    }

    fun addClient(newClient: Client) {
        checkClientInfo(newClient) ?: return
        viewModelScope.launch(Dispatchers.IO) {
            clientLocalDataSource.insertClient(newClient)
            val tempClientList = clientMutableList.value?.toMutableList() ?: return@launch
            tempClientList.add(newClient)
            clientMutableList.postValue(tempClientList)
            _successInAddClient.postValue(true)
        }
    }

    private fun checkClientInfo(client: Client): Unit? {
        with(client) {
            if (name == "" || rrmFront == "" || rrmBack == "" || callMiddle == "" || callBack == "") {
                _clientInfoError.value = true
                return null
            }
        }
        return Unit
    }

    fun doneClientInfoError() {
        _clientInfoError.value = false
    }

    fun doneSuccessInAddClient() {
        _successInAddClient.value = false
    }
}
