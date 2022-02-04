package com.kldaji.loanclientmanagement.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recentSearchWord")
data class RecentSearchWord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val word: String,
    @ColumnInfo
    val millis: Long,
)
