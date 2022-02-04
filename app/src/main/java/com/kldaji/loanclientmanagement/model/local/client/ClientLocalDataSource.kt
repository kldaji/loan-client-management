package com.kldaji.loanclientmanagement.model.local.client

import com.kldaji.loanclientmanagement.model.data.Client

interface ClientLocalDataSource {
    suspend fun getAllClients(): List<Client>
    suspend fun insertClient(client: Client)
    suspend fun deleteClient(client: Client)
    suspend fun updateClient(client: Client)
}
