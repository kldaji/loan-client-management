package com.kldaji.loanclientmanagement.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client")
data class Client(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    val loan: Loan,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val rmmFront: String,
    @ColumnInfo
    val rmmBack: String,
    @ColumnInfo
    val callMiddle: String,
    @ColumnInfo
    val callBack: String,
    @ColumnInfo
    val meetingDate: String,
    @ColumnInfo
    val loanStartDate: String,
)

enum class Loan {
    SECURITY, JEONSE
}
