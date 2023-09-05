package com.ngtuankhanh.soundify.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.R
import com.ngtuankhanh.soundify.databinding.ItemFavoriteGridBinding
import com.ngtuankhanh.soundify.ui.models.PlaylistIcon

class FavoritePlaylistAdapter : ListAdapter<PlaylistIcon, FavoritePlaylistAdapter.FavoritePlaylistViewHolder>(PlaylistDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePlaylistViewHolder {
        val binding = ItemFavoriteGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritePlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritePlaylistViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FavoritePlaylistViewHolder(private val binding: ItemFavoriteGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(playlistIcon: PlaylistIcon) {
            binding.playlistName.text = playlistIcon.Name
            Glide.with(binding.root)
                .load(playlistIcon.BackgroundImage.URL)
                .placeholder(R.drawable.ic_home)
                .into(binding.playlistImage)
        }
    }

    class PlaylistDiffCallback : DiffUtil.ItemCallback<PlaylistIcon>() {
        override fun areItemsTheSame(oldItem: PlaylistIcon, newItem: PlaylistIcon): Boolean = oldItem.ID == newItem.ID
        override fun areContentsTheSame(oldItem: PlaylistIcon, newItem: PlaylistIcon): Boolean = oldItem == newItem
    }
}
