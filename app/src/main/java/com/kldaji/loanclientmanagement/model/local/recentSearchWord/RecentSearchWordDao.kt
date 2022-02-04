package com.kldaji.loanclientmanagement.model.local.recentSearchWord

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kldaji.loanclientmanagement.model.data.RecentSearchWord

@Dao
interface RecentSearchWordDao {
    @Query("SELECT * FROM recentSearchWord ORDER BY millis DESC")
    fun getAllRecentSearchWords(): List<RecentSearchWord>

    @Insert
    fun insertRecentSearchWord(recentSearchWord: RecentSearchWord)

    @Update
    fun updateRecentSearchWord(recentSearchWord: RecentSearchWord)

    @Delete
    fun deleteRecentSearchWord(recentSearchWord: RecentSearchWord)
}
