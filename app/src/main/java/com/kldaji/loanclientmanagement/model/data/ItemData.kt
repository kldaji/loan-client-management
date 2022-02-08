package com.kldaji.loanclientmanagement.model.data

sealed class ItemData {
    abstract val id: Long
}

data class EmptyData(override val id: Long = 0L, val text: String): ItemData()
