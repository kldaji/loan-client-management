package com.kldaji.loanclientmanagement.ui.client.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kldaji.loanclientmanagement.databinding.ItemDocImageBinding

class DocsImageAdapter :
    androidx.recyclerview.widget.ListAdapter<Uri, DocsImageAdapter.DocImageViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocImageViewHolder =
        DocImageViewHolder(
            ItemDocImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: DocImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DocImageViewHolder(private val binding: ItemDocImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(uri: Uri) {
            binding.ivImageDoc.setImageURI(uri)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Uri>() {
            override fun areItemsTheSame(oldItem: Uri, newItem: Uri): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Uri, newItem: Uri): Boolean =
                oldItem == newItem
        }
    }
}
