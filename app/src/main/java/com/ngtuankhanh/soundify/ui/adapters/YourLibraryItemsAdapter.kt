package com.ngtuankhanh.soundify.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.databinding.ItemDisplayBinding
import com.ngtuankhanh.soundify.ui.models.Item

class YourLibraryItemsAdapter(
    private val onClickListener: (Item) -> Unit
) : ListAdapter<Item, YourLibraryItemsAdapter.YourLibraryItemViewHolder>(
    YourLibraryItemDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourLibraryItemViewHolder {
        val binding =
            ItemDisplayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return YourLibraryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: YourLibraryItemViewHolder, position: Int) {
        val libraryItem = getItem(position)
        holder.bind(libraryItem)
        holder.itemView.setOnClickListener { onClickListener(libraryItem) }
    }

    inner class YourLibraryItemViewHolder(private val binding: ItemDisplayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                itemNameText.text = item.name
                Glide.with(itemView.context).load(item.imageUrl).into(itemImage)
                itemTypeText.text = item.type.name
            }
        }
    }

    class YourLibraryItemDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean {
            return oldItem == newItem
        }
    }
}
