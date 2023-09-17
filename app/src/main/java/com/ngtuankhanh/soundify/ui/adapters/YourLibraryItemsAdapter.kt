package com.ngtuankhanh.soundify.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.databinding.ItemYourLibraryBinding
import com.ngtuankhanh.soundify.ui.models.YourLibraryItem

class YourLibraryItemsAdapter(
    private val onClickListener: (YourLibraryItem) -> Unit
) : ListAdapter<YourLibraryItem, YourLibraryItemsAdapter.YourLibraryItemViewHolder>(
    YourLibraryItemDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourLibraryItemViewHolder {
        val binding =
            ItemYourLibraryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return YourLibraryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: YourLibraryItemViewHolder, position: Int) {
        val libraryItem = getItem(position)
        holder.bind(libraryItem)
        holder.itemView.setOnClickListener { onClickListener(libraryItem) }
    }

    inner class YourLibraryItemViewHolder(private val binding: ItemYourLibraryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(yourLibraryItem: YourLibraryItem) {
            binding.apply {
                libraryItemNameText.text = yourLibraryItem.name
                Glide.with(itemView.context).load(yourLibraryItem.imageUrl).into(libraryItemImage)
                libraryItemTypeText.text = yourLibraryItem.type.name
            }
        }
    }

    class YourLibraryItemDiffCallback : DiffUtil.ItemCallback<YourLibraryItem>() {
        override fun areItemsTheSame(oldItem: YourLibraryItem, newItem: YourLibraryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: YourLibraryItem,
            newItem: YourLibraryItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}
