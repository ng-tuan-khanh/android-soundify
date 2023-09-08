package com.ngtuankhanh.soundify.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ngtuankhanh.soundify.databinding.ItemAlbumSeriesBinding
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.R
import com.ngtuankhanh.soundify.ui.models.DisplayAlbum

class AlbumAdapter : ListAdapter<DisplayAlbum, AlbumAdapter.AlbumViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding =
            ItemAlbumSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AlbumViewHolder(private val binding: ItemAlbumSeriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: DisplayAlbum) {
            binding.apply {
                songPreviewName.text = album.name
                albumName.text = album.name
                artistName.text = album.artistName
                songCount.text = "${album.totalTracks} bài hát"
                Glide.with(itemView.context).load(album.imageUrl).into(albumImage)

                soundToggleButton.setImageResource(if (album.isSoundOn) R.drawable.ic_volume_on else R.drawable.ic_volume_off)

                soundToggleButton.setOnClickListener {
                    album.isSoundOn = !album.isSoundOn
                    notifyItemChanged(adapterPosition)
                }
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<DisplayAlbum>() {
        override fun areItemsTheSame(oldItem: DisplayAlbum, newItem: DisplayAlbum): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DisplayAlbum, newItem: DisplayAlbum): Boolean {
            return oldItem == newItem
        }
    }
}
