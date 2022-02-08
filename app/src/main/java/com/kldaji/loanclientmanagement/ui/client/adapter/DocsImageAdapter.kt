package com.kldaji.loanclientmanagement.ui.client.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kldaji.loanclientmanagement.databinding.ItemDocImageBinding
import com.kldaji.loanclientmanagement.databinding.ItemEmptyImageBinding
import com.kldaji.loanclientmanagement.model.data.EmptyData
import com.kldaji.loanclientmanagement.model.data.ImageUri
import com.kldaji.loanclientmanagement.model.data.ItemData

class DocsImageAdapter :
    androidx.recyclerview.widget.ListAdapter<ItemData, RecyclerView.ViewHolder>(diffUtil) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is EmptyData) EMPTY_TYPE
        else RecentSearchWordsAdapter.NOT_EMPTY_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            EMPTY_TYPE -> EmptyViewHolder(ItemEmptyImageBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false))
            else -> DocImageViewHolder(
                ItemDocImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EmptyViewHolder -> holder.bind(getItem(position) as EmptyData)
            is DocImageViewHolder -> holder.bind(getItem(position) as ImageUri)
        }
    }

    inner class DocImageViewHolder(private val binding: ItemDocImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageUri: ImageUri) {
            binding.ivImageDoc.setImageURI(imageUri.uri)
        }
    }

    class EmptyViewHolder(private val binding: ItemEmptyImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(emptyData: EmptyData) {
            binding.tvEmpty.text = emptyData.text
        }
    }

    companion object {
        const val EMPTY_TYPE = 0
        const val NOT_EMPTY_TYPE = 1

        val diffUtil = object : DiffUtil.ItemCallback<ItemData>() {
            override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean =
                oldItem == newItem
        }
    }
}
