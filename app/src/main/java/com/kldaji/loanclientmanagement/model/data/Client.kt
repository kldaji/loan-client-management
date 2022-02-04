package com.kldaji.loanclientmanagement.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client")
data class Client(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val loan: Loan,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val rrmFront: String,
    @ColumnInfo
    val rrmBack: String,
    @ColumnInfo
    val callMiddle: String,
    @ColumnInfo
    val callBack: String,
    @ColumnInfo
    val meetingDate: String,
    @ColumnInfo
    val loanStartDate: String,
    @ColumnInfo
    val docs: String,
)

enum class Loan {
    SECURITY, JEONSE
}
