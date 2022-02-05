package com.kldaji.loanclientmanagement.ui.client.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kldaji.loanclientmanagement.databinding.ItemClientBinding
import com.kldaji.loanclientmanagement.databinding.ItemEmptyBinding
import com.kldaji.loanclientmanagement.model.data.Client
import com.kldaji.loanclientmanagement.model.data.EmptyData
import com.kldaji.loanclientmanagement.model.data.ItemData
import com.kldaji.loanclientmanagement.utils.dp

class ClientsAdapter(private val itemClickListener: ItemClickListener) :
    ListAdapter<ItemData, RecyclerView.ViewHolder>(diffUtil) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is EmptyData) EMPTY_TYPE
        else NOT_EMPTY_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            EMPTY_TYPE -> EmptyViewHolder(ItemEmptyBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false))
            else -> ClientViewHolder(ItemClientBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ClientViewHolder -> holder.bind(getItem(position) as Client)
            is EmptyViewHolder -> holder.bind(getItem(position) as EmptyData)
        }
    }

    inner class ClientViewHolder(private val binding: ItemClientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClickListener.onItemClick(adapterPosition)
            }
        }

        fun bind(_client: Client) {
            binding.client = _client
        }
    }

    class EmptyViewHolder(private val binding: ItemEmptyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.tvEmpty.setPadding(0, 36.dp, 0, 36.dp)
        }

        fun bind(emptyData: EmptyData) {
            binding.tvEmpty.text = emptyData.text
        }
    }

    companion object {
        const val EMPTY_TYPE = 0
        const val NOT_EMPTY_TYPE = 1

        private val diffUtil = object : DiffUtil.ItemCallback<ItemData>() {
            override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean =
                oldItem == newItem
        }
    }
}
