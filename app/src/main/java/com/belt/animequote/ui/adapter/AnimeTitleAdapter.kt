package com.belt.animequote.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.belt.animequote.databinding.AnimeTitleItemBinding
import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.infrastructure.primary.mapper.ViewAnimeTitle

class AnimeTitleAdapter(private val listener: AnimeTitleViewHolder.OnAnimeTitleClickListener) : ListAdapter<AnimeTitle, AnimeTitleAdapter.AnimeTitleViewHolder>(
    AnimeTitleDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeTitleViewHolder {
        return AnimeTitleViewHolder(
            AnimeTitleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AnimeTitleViewHolder, position: Int) {
        val animeTitle = getItem(position)
        if (animeTitle != null) {
            holder.bind(animeTitle, listener)
        }
    }

    class AnimeTitleViewHolder(private val binding: AnimeTitleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        interface OnAnimeTitleClickListener {
            fun onClick(viewAnimeTitle: ViewAnimeTitle)
        }

        fun bind(item: AnimeTitle, listener: OnAnimeTitleClickListener) {
            binding.animeTitle.text = item.value
            binding.animeTitle.setOnClickListener {
                listener.onClick(ViewAnimeTitle(item.value))
            }
        }
    }
}

private class AnimeTitleDiffCallback : DiffUtil.ItemCallback<AnimeTitle>() {
    override fun areItemsTheSame(oldItem: AnimeTitle, newItem: AnimeTitle): Boolean {
        return oldItem.value == newItem.value
    }

    override fun areContentsTheSame(oldItem: AnimeTitle, newItem: AnimeTitle): Boolean {
        return oldItem == newItem
    }
}