package com.kldaji.loanclientmanagement.model.data

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageUri(override val id: Long = 0L, val uri: Uri) : ItemData(), Parcelable
