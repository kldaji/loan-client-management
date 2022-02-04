package com.kldaji.loanclientmanagement

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kldaji.loanclientmanagement.model.data.Client
import com.kldaji.loanclientmanagement.model.data.RecentSearchWord
import com.kldaji.loanclientmanagement.model.local.client.ClientDao
import com.kldaji.loanclientmanagement.model.local.recentSearchWord.RecentSearchWordDao

@Database(entities = [Client::class, RecentSearchWord::class], version = 1)
abstract class LoanClientManagementDatabase : RoomDatabase() {
    abstract fun clientDao(): ClientDao
    abstract fun recentSearchWordDao(): RecentSearchWordDao
}
