package com.ngtuankhanh.soundify.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.ngtuankhanh.soundify.ui.models.TrackCollection


class TrackCollectionDiffCallback: DiffUtil.ItemCallback<TrackCollection>() {
    override fun areItemsTheSame(oldItem: TrackCollection, newItem: TrackCollection): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TrackCollection, newItem: TrackCollection): Boolean {
        return oldItem == newItem
    }
}