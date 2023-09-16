package com.ngtuankhanh.soundify.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.ui.models.SearchItem
import com.ngtuankhanh.soundify.databinding.ItemSearchResultBinding
import com.ngtuankhanh.soundify.ui.models.ItemType
import com.ngtuankhanh.soundify.ui.search.OnSearchItemClickListener

class SearchItemsAdapter(private val listener: OnSearchItemClickListener) :
    ListAdapter<SearchItem, SearchItemsAdapter.SearchViewHolder>(SearchItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class SearchViewHolder(private val binding: ItemSearchResultBinding, private val listener: OnSearchItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchItem) {
            // ... các phần khác của mã ...

            binding.root.setOnClickListener {
                when (item.type) {
                    ItemType.Track -> listener.onTrackClicked(item)
                    ItemType.Artist -> listener.onArtistClicked(item)
                    ItemType.Playlist -> listener.onPlaylistClicked(item)
                    ItemType.Album -> { /* Ignored for now */ }
                }
            }
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
