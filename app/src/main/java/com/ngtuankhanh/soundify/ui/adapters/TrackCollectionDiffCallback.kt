package com.ngtuankhanh.soundify.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.ngtuankhanh.soundify.ui.models.CollectionItem


class TrackCollectionDiffCallback: DiffUtil.ItemCallback<CollectionItem>() {
    override fun areItemsTheSame(oldItem: CollectionItem, newItem: CollectionItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CollectionItem, newItem: CollectionItem): Boolean {
        return oldItem == newItem
    }
}