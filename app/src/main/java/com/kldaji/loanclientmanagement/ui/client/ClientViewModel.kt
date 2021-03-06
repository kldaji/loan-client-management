package com.kldaji.loanclientmanagement.ui.client

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kldaji.loanclientmanagement.model.data.Client
import com.kldaji.loanclientmanagement.model.data.ImageUri
import com.kldaji.loanclientmanagement.model.local.client.ClientLocalDataSource
import com.kldaji.loanclientmanagement.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(private val clientLocalDataSource: ClientLocalDataSource) :
    ViewModel() {
    private val clientMutableList = MutableLiveData<List<Client>>()
    val clientList: LiveData<List<Client>> = clientMutableList

    private val _searchWord = MutableLiveData("")
    private val searchWord: LiveData<String> = _searchWord

    val resultClientList: LiveData<List<Client>> = Transformations.map(searchWord) { word ->
        if (word == "") listOf()
        else clientList.value?.filter { client -> client.name.contains(word) }
    }

    private val _clientInfoError = MutableLiveData<Boolean>()
    val clientInfoError: LiveData<Boolean> = _clientInfoError
    private val _successInAddClient = MutableLiveData<Boolean>()
    val successInAddClient: LiveData<Boolean> = _successInAddClient

    private val _editable = MutableLiveData<Event<Boolean>>()
    val editable: LiveData<Event<Boolean>> = _editable

    private val _docImageList = MutableLiveData<List<ImageUri>>(listOf())
    val docImageList: LiveData<List<ImageUri>> = _docImageList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            clientMutableList.postValue(clientLocalDataSource.getAllClients())
        }
    }

    fun setSearchWord(word: String) {
        _searchWord.value = word
    }

    fun addClient(newClient: Client) {
        checkClientInfo(newClient) ?: return
        viewModelScope.launch(Dispatchers.IO) {
            clientLocalDataSource.insertClient(newClient)
            val tempClientList = clientMutableList.value?.toMutableList() ?: return@launch
            tempClientList.add(newClient)
            tempClientList.sortBy { it.name }
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

    fun toggleEditable() {
        val flag = _editable.value?.peekContent() ?: false
        _editable.value = Event(!flag)
    }

    fun updateClient(oldClient: Client, newClient: Client) {
        checkClientInfo(newClient) ?: return
        viewModelScope.launch(Dispatchers.IO) {
            clientLocalDataSource.updateClient(newClient)
            val tempClientList = clientMutableList.value?.toMutableList() ?: return@launch
            val targetIndex = tempClientList.indexOf(oldClient)
            if (targetIndex != -1) {
                tempClientList[targetIndex] = newClient
            }
            clientMutableList.postValue(tempClientList)
        }
    }

    fun addImage(uri: Uri) {
        val tempDocImageList = _docImageList.value?.toMutableList() ?: return
        tempDocImageList.add(ImageUri(DateTime.now().millis, uri))
        _docImageList.value = tempDocImageList
    }

    fun clearImages() {
        _docImageList.value = listOf()
    }
}
