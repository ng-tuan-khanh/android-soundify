package com.ngtuankhanh.soundify.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ngtuankhanh.soundify.databinding.ItemFeaturedPlaylistBinding
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.ui.models.CollectionItem

class FeaturedPlaylistsAdapter(
    private val onPlaylistClick: (CollectionItem) -> Unit
) : ListAdapter<CollectionItem, FeaturedPlaylistsAdapter.FeaturedPlaylistsViewHolder>(TrackCollectionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedPlaylistsViewHolder {
        val binding = ItemFeaturedPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeaturedPlaylistsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeaturedPlaylistsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FeaturedPlaylistsViewHolder(private val binding: ItemFeaturedPlaylistBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onPlaylistClick(getItem(position))
                }
            }
        }

        fun bind(collectionItem: CollectionItem) {
            binding.apply {
                albumNameText.text = collectionItem.name
                artistName.text = collectionItem.artistName
                trackCountText.text = "${collectionItem.totalTracks} tracks"
                Glide.with(itemView.context).load(collectionItem.imageUrl).into(albumImage)
            }
        }
    }
}
