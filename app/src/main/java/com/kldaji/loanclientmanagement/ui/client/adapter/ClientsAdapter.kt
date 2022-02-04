package com.kldaji.loanclientmanagement.ui.client.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kldaji.loanclientmanagement.databinding.ItemClientBinding
import com.kldaji.loanclientmanagement.model.data.Client

class ClientsAdapter(private val itemClickListener: ItemClickListener) :
    ListAdapter<Client, ClientsAdapter.ClientViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder =
        ClientViewHolder(ItemClientBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.bind(getItem(position))
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

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Client>() {
            override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean =
                oldItem == newItem
        }
    }
}
