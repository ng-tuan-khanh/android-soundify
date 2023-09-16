package com.ngtuankhanh.soundify.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.ui.models.SearchItem
import com.ngtuankhanh.soundify.databinding.ItemSearchResultBinding

class SearchItemsAdapter :
    ListAdapter<SearchItem, SearchItemsAdapter.SearchViewHolder>(SearchItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class SearchViewHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchItem) {
            binding.resultNameText.text = item.name
            binding.resultTypeText.text = item.type.toString()
            Glide.with(binding.root)
                .load(item.avatarImage.url)
                .into(binding.resultImage)
        }
    }

    class SearchItemDiffCallback : DiffUtil.ItemCallback<SearchItem>() {
        override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem == newItem
        }
    }
}
