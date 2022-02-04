package com.kldaji.loanclientmanagement

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kldaji.loanclientmanagement.model.data.Client
import com.kldaji.loanclientmanagement.model.local.client.ClientDao

@Database(entities = [Client::class], version = 1)
abstract class LoanClientManagementDatabase : RoomDatabase() {
    abstract fun clientDao(): ClientDao
}
