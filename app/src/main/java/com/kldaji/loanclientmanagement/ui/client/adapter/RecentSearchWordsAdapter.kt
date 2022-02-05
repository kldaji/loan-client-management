package com.kldaji.loanclientmanagement.ui.client.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kldaji.loanclientmanagement.databinding.ItemEmptyBinding
import com.kldaji.loanclientmanagement.databinding.ItemRecentSearchWordBinding
import com.kldaji.loanclientmanagement.model.data.EmptyData
import com.kldaji.loanclientmanagement.model.data.ItemData
import com.kldaji.loanclientmanagement.model.data.RecentSearchWord
import com.kldaji.loanclientmanagement.utils.dp

class RecentSearchWordsAdapter(private val itemClickListener: ItemClickListener, private val chipCloseIconClickListener: ChipCloseIconClickListener) :
    ListAdapter<ItemData, RecyclerView.ViewHolder>(diffUtil) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is EmptyData) EMPTY_TYPE
        else NOT_EMPTY_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            EMPTY_TYPE -> EmptyViewHolder(ItemEmptyBinding.inflate(LayoutInflater.from(
                parent.context),
                parent,
                false))
            else -> RecentSearchWordViewHolder(ItemRecentSearchWordBinding.inflate(LayoutInflater.from(
                parent.context),
                parent,
                false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecentSearchWordViewHolder -> holder.bind(getItem(position) as RecentSearchWord)
            is EmptyViewHolder -> holder.bind(getItem(position) as EmptyData)
        }
    }

    inner class RecentSearchWordViewHolder(private val binding: ItemRecentSearchWordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.cRecentSearchWord.setOnClickListener {
                itemClickListener.onItemClick(adapterPosition)
            }
            binding.cRecentSearchWord.setOnCloseIconClickListener {
                chipCloseIconClickListener.onCloseIconClick(adapterPosition)
            }
        }

        fun bind(_recentSearchWord: RecentSearchWord) {
            binding.recentSearchWord = _recentSearchWord
        }
    }

    class EmptyViewHolder(private val binding: ItemEmptyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.tvEmpty.setPadding(0, 8.dp, 0, 8.dp)
        }

        fun bind(emptyData: EmptyData) {
            binding.tvEmpty.text = emptyData.text
        }
    }

    companion object {
        const val EMPTY_TYPE = 0
        const val NOT_EMPTY_TYPE = 1

        private val diffUtil = object : DiffUtil.ItemCallback<ItemData>() {
            override fun areItemsTheSame(
                oldItem: ItemData,
                newItem: ItemData,
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ItemData,
                newItem: ItemData,
            ): Boolean = oldItem == newItem
        }
    }

    interface ChipCloseIconClickListener {
        fun onCloseIconClick(position: Int)
    }
}
