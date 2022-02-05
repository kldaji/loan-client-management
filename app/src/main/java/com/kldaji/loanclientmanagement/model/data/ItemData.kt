package com.kldaji.loanclientmanagement.model.data

sealed class ItemData {
    abstract val id: Int
}

data class EmptyData(override val id: Int = 0, val text: String): ItemData()
