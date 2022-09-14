package com.belt.animequote.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.belt.animequote.databinding.FavoriteAnimeTitleItemBinding
import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.infrastructure.primary.mapper.ViewAnimeTitle

class FavoritesAnimeTitleAdapter(private val listener: FavoriteAnimeTitleViewHolder.OnAnimeTitleClickListener) :
    ListAdapter<AnimeTitle, FavoritesAnimeTitleAdapter.FavoriteAnimeTitleViewHolder>(FavoriteAnimeTitleDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAnimeTitleViewHolder {
        return FavoriteAnimeTitleViewHolder(
            FavoriteAnimeTitleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteAnimeTitleViewHolder, position: Int) {
        val animeTitle = getItem(position)
        if (animeTitle != null) {
            holder.bind(animeTitle, listener)
        }
    }

    class FavoriteAnimeTitleViewHolder(private val binding: FavoriteAnimeTitleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        interface OnAnimeTitleClickListener {
            fun onClick(viewAnimeTitle: ViewAnimeTitle)
            fun onRemoveFavoriteIconClick(viewAnimeTitle: ViewAnimeTitle)
        }

        fun bind(item: AnimeTitle, listener: OnAnimeTitleClickListener) {
            val viewAnimeTitle = ViewAnimeTitle(item.value)

            binding.animeTitle.text = item.value
            binding.root.setOnClickListener { listener.onClick(viewAnimeTitle) }
            binding.bookmarkRemoveIcon.setOnClickListener { listener.onRemoveFavoriteIconClick(viewAnimeTitle) }
        }
    }
}

private class FavoriteAnimeTitleDiffCallback : DiffUtil.ItemCallback<AnimeTitle>() {
    override fun areItemsTheSame(oldItem: AnimeTitle, newItem: AnimeTitle): Boolean {
        return oldItem.value == newItem.value
    }

    override fun areContentsTheSame(oldItem: AnimeTitle, newItem: AnimeTitle): Boolean {
        return oldItem == newItem
    }
}