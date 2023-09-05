package com.ngtuankhanh.soundify.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ngtuankhanh.soundify.databinding.ItemAlbumSeriesBinding
import com.bumptech.glide.Glide
import com.ngtuankhanh.soundify.R
import com.ngtuankhanh.soundify.data.models.DisplayAlbum

class SeriAlbumAdapter(private val albums: List<DisplayAlbum>) : RecyclerView.Adapter<SeriAlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    inner class AlbumViewHolder(private val binding: ItemAlbumSeriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(album: DisplayAlbum) {
            binding.apply {
                songPreviewName.text = album.Name
                albumName.text = album.Name
                artistName.text = album.ArtistName
                songCount.text = "${album.TotalTracks} bài hát"
                Glide.with(itemView.context).load(album.ImageURL).into(albumImage)

                soundToggleButton.setImageResource(if (album.isSoundOn) R.drawable.ic_volume_on else R.drawable.ic_volume_off)

                soundToggleButton.setOnClickListener {
                    album.isSoundOn = !album.isSoundOn
                    notifyItemChanged(adapterPosition)
                }
            }
        }
    }
}
