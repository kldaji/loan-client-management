package com.kldaji.loanclientmanagement.ui.client.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kldaji.loanclientmanagement.databinding.ItemRecentSearchWordBinding
import com.kldaji.loanclientmanagement.model.data.RecentSearchWord

class RecentSearchWordsAdapter :
    ListAdapter<RecentSearchWord, RecentSearchWordsAdapter.RecentSearchWordViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchWordViewHolder =
        RecentSearchWordViewHolder(ItemRecentSearchWordBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    override fun onBindViewHolder(holder: RecentSearchWordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RecentSearchWordViewHolder(private val binding: ItemRecentSearchWordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(_recentSearchWord: RecentSearchWord) {
            binding.recentSearchWord = _recentSearchWord
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<RecentSearchWord>() {
            override fun areItemsTheSame(
                oldItem: RecentSearchWord,
                newItem: RecentSearchWord,
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: RecentSearchWord,
                newItem: RecentSearchWord,
            ): Boolean = oldItem == newItem
        }
    }
}
