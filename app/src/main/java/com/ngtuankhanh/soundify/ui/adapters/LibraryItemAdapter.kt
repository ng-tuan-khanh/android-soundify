package com.ngtuankhanh.soundify.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ngtuankhanh.soundify.databinding.ItemLibraryBinding
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.ui.models.LibraryItem

class LibraryItemAdapter(private val libraryItems: List<LibraryItem>) : RecyclerView.Adapter<LibraryItemAdapter.LibraryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryItemViewHolder {
        val binding = ItemLibraryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibraryItemViewHolder(binding)
    }

    override fun getItemCount(): Int = libraryItems.size

    override fun onBindViewHolder(holder: LibraryItemViewHolder, position: Int) {
        holder.bind(libraryItems[position])
    }

    inner class LibraryItemViewHolder(private val binding: ItemLibraryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(libraryItem: LibraryItem) {
            binding.apply {
                itemName.text = libraryItem.name
                Glide.with(itemView.context).load(libraryItem.avatarImage?.url).into(itemAvatar)
                itemType.text = libraryItem.type.name
            }
        }
    }
}
