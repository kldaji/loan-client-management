package com.kldaji.loanclientmanagement.model.local.recentSearchWord

import com.kldaji.loanclientmanagement.model.data.RecentSearchWord

class RecentSearchWordLocalDataSourceImpl(private val recentSearchWordDao: RecentSearchWordDao) :
    RecentSearchWordLocalDataSource {

    override suspend fun getAllRecentSearchWords(): List<RecentSearchWord> {
        return recentSearchWordDao.getAllRecentSearchWords()
    }

    override suspend fun insertRecentSearchWord(recentSearchWord: RecentSearchWord) {
        recentSearchWordDao.insertRecentSearchWord(recentSearchWord)
    }

    override suspend fun updateRecentSearchWord(recentSearchWord: RecentSearchWord) {
        recentSearchWordDao.updateRecentSearchWord(recentSearchWord)
    }

    override suspend fun deleteRecentSearchWord(recentSearchWord: RecentSearchWord) {
        recentSearchWordDao.deleteRecentSearchWord(recentSearchWord)
    }
}
