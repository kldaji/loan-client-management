package com.kldaji.loanclientmanagement.model.local.recentSearchWord

import com.kldaji.loanclientmanagement.model.data.RecentSearchWord

interface RecentSearchWordLocalDataSource {
    suspend fun getAllRecentSearchWords(): List<RecentSearchWord>
    suspend fun insertRecentSearchWord(recentSearchWord: RecentSearchWord)
    suspend fun updateRecentSearchWord(recentSearchWord: RecentSearchWord)
    suspend fun deleteRecentSearchWord(recentSearchWord: RecentSearchWord)
}
