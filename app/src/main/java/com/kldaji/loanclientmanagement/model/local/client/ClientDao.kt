package com.kldaji.loanclientmanagement.model.local.client

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kldaji.loanclientmanagement.model.data.Client

@Dao
interface ClientDao {
    @Query("SELECT * FROM client ORDER BY name ASC")
    fun getAllClients(): List<Client>

    @Insert
    fun insertClient(client: Client)

    @Delete
    fun deleteClient(client: Client)

    @Update
    fun updateClient(client: Client)
}
