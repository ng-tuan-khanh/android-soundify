package com.ngtuankhanh.soundify.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ngtuankhanh.soundify.databinding.ItemFeaturedPlaylistBinding
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.ui.models.TrackCollection

class FeaturedPlaylistsAdapter(
    private val onPlaylistClick: (TrackCollection) -> Unit,
    private val onPlayClick: () -> Unit
) : ListAdapter<TrackCollection, FeaturedPlaylistsAdapter.FeaturedPlaylistsViewHolder>(TrackCollectionDiffCallback()) {

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

            binding.playButton.setOnClickListener {
                onPlayClick()
            }
        }

        fun bind(trackCollection: TrackCollection) {
            binding.apply {
                trackNameText.text = trackCollection.name
                albumNameText.text = trackCollection.name
                artistName.text = trackCollection.artistName
                trackCountText.text = "${trackCollection.totalTracks} tracks"
                Glide.with(itemView.context).load(trackCollection.imageUrl).into(albumImage)
            }
        }
    }
}
