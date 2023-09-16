package com.ngtuankhanh.soundify.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.databinding.ItemTopPlaylistBinding
import com.ngtuankhanh.soundify.ui.models.TrackCollection

class TopPlaylistsAdapter(private val onItemClick: (TrackCollection) -> Unit) : ListAdapter<TrackCollection, TopPlaylistsAdapter.TopPlaylistViewHolder>(TrackCollectionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPlaylistViewHolder {
        val binding = ItemTopPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopPlaylistViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: TopPlaylistViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TopPlaylistViewHolder(private val binding: ItemTopPlaylistBinding, private val onItemClick: (TrackCollection) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    onItemClick(item)
                }
            }
        }

        fun bind(trackCollection: TrackCollection) {
            binding.playlistName.text = trackCollection.name
            Glide.with(binding.root)
                .load(trackCollection.imageUrl)
                .into(binding.playlistImage)
        }
    }
}
