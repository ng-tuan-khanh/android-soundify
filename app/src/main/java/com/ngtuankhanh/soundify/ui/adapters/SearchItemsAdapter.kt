package com.ngtuankhanh.soundify.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.databinding.ItemDisplayBinding
import com.ngtuankhanh.soundify.ui.models.Item
import com.ngtuankhanh.soundify.ui.models.ItemType
import com.ngtuankhanh.soundify.ui.search.OnSearchItemClickListener

class SearchItemsAdapter(private val onClickListener: OnSearchItemClickListener) :
    ListAdapter<Item, SearchItemsAdapter.SearchViewHolder>(SearchItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemDisplayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class SearchViewHolder(private val binding: ItemDisplayBinding, private val onClickListener: OnSearchItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.root.setOnClickListener {
                when (item.type) {
                    ItemType.Track -> onClickListener.onTrackClicked(item)
                    ItemType.Artist -> onClickListener.onArtistClicked(item)
                    ItemType.Playlist -> onClickListener.onCollectionClicked(item)
                    ItemType.Album -> onClickListener.onCollectionClicked(item)
                }
            }
            binding.apply {
                itemNameText.text = item.name
                Glide.with(itemView.context).load(item.imageUrl).into(itemImage)
                itemTypeText.text = item.type.name
            }
        }
    }

    class SearchItemDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}
