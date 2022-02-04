package com.kldaji.loanclientmanagement.model.local.client

import com.kldaji.loanclientmanagement.model.data.Client
import javax.inject.Inject

class ClientLocalDataSourceImpl @Inject constructor(private val clientDao: ClientDao) :
    ClientLocalDataSource {
    override suspend fun getAllClients(): List<Client> {
        return clientDao.getAllClients()
    }

    override suspend fun insertClient(client: Client) {
        clientDao.insertClient(client)
    }

    override suspend fun deleteClient(client: Client) {
        clientDao.deleteClient(client)
    }

    override suspend fun updateClient(client: Client) {
        clientDao.updateClient(client)
    }
}
