package com.ngtuankhanh.soundify.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.databinding.ItemTrackBinding
import com.ngtuankhanh.soundify.ui.models.Image
import com.ngtuankhanh.soundify.ui.models.Track

class TrackListAdapter(private val onPlayButtonClick: (Track) -> Unit) : ListAdapter<Track, TrackListAdapter.TrackViewHolder>(TrackDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val binding = ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TrackViewHolder(private val binding: ItemTrackBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.itemTrackPlayButton.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                onPlayButtonClick(getItem(position))
            }
        }

        fun bind(track: Track) {
            binding.itemTrackName.text = track.name
            Glide.with(binding.root)
                .load(track.backgroundImage.url)
                .into(binding.itemTrackImage)
            // Here, I'm assuming you may want to also display the artist's name.
            // If you add an artist's name to the Track model in the future, this is where you would set it.
            // binding.itemTrackArtistName.text = track.artistName
        }
    }

    class TrackDiffCallback : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean = oldItem == newItem
    }
}
