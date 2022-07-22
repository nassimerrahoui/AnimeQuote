package com.example.animequote.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.animequote.databinding.AnimeTitleItemBinding
import com.example.animequote.domain.quote.entity.AnimeTitle

class AnimeTitleAdapter : ListAdapter<AnimeTitle, AnimeTitleAdapter.AnimeTitleViewHolder>(AnimeTitleDiffCallback()) {
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
            holder.bind(animeTitle)
        }
    }

    class AnimeTitleViewHolder(private val binding: AnimeTitleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AnimeTitle) {
            binding.animeTitle.text = item.value
            binding.animeTitle.setOnClickListener {
                // FIXME: implement redirection to a new fragment ? to display the quotes of the anime
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