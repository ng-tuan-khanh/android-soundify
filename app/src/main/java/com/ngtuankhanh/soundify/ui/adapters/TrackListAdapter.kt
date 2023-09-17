package com.ngtuankhanh.soundify.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.databinding.ItemTrackBinding
import com.ngtuankhanh.soundify.ui.models.TrackItem

class TrackListAdapter(private val onPlayButtonClick: (TrackItem) -> Unit) :
    ListAdapter<TrackItem, TrackListAdapter.TrackViewHolder>(TrackDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val binding = ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TrackViewHolder(private val binding: ItemTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.playButton.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                onPlayButtonClick(getItem(position))
            }
        }

        fun bind(trackItem: TrackItem) {
            binding.itemNameText.text = trackItem.name
            Glide.with(binding.root)
                .load(trackItem.imageUrl)
                .into(binding.itemImage)
            binding.itemTypeText.text = trackItem.artists.joinToString(", ")
        }
    }

    class TrackDiffCallback : DiffUtil.ItemCallback<TrackItem>() {
        override fun areItemsTheSame(oldItem: TrackItem, newItem: TrackItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TrackItem, newItem: TrackItem): Boolean =
            oldItem == newItem
    }
}
