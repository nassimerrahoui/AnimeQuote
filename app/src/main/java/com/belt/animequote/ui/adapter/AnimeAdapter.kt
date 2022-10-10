package com.belt.animequote.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.belt.animequote.databinding.AnimeItemBinding
import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.infrastructure.primary.mapper.ViewAnime

class AnimeAdapter(private val listener: AnimeViewHolder.OnAnimeClickListener) :
    ListAdapter<ViewAnime, AnimeAdapter.AnimeViewHolder>(AnimeDiffCallback()),
    Filterable {

    private var originalList: List<ViewAnime> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        return AnimeViewHolder(
            AnimeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = getItem(position)
        if (anime != null) {
            holder.bind(anime, listener)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                return FilterResults().apply {
                    values = if (constraint.isNullOrEmpty())
                        originalList
                    else
                        originalList.filter { viewAnime -> viewAnime.value.contains(constraint, true) }
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                val filteredList = results?.values
                submitList(filteredList as List<ViewAnime>, true)
            }
        }
    }

    override fun submitList(list: List<ViewAnime>?) {
        submitList(list, false)
    }

    private fun submitList(list: List<ViewAnime>?, filtered: Boolean) {
        if (!filtered) originalList = list ?: listOf()
        super.submitList(list)
    }

    class AnimeViewHolder(private val binding: AnimeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        interface OnAnimeClickListener {
            fun onClick(viewAnime: ViewAnime)
            fun onFavoriteIconClick(animeTitle: AnimeTitle)
        }

        fun bind(item: ViewAnime, listener: OnAnimeClickListener) {
            binding.animeTitle.text = item.value
            binding.root.setOnClickListener { listener.onClick(item) }
            initFavoriteButton(item, listener)
        }

        private fun initFavoriteButton(item: ViewAnime, listener: OnAnimeClickListener) {
            if (item.isFavorite) binding.bookmarkBorderIcon.visibility = View.INVISIBLE
            else {
                binding.bookmarkBorderIcon.setOnClickListener {
                    listener.onFavoriteIconClick(AnimeTitle(item.value))
                    item.isFavorite = true
                    binding.bookmarkBorderIcon.visibility = View.INVISIBLE
                }
                binding.bookmarkBorderIcon.visibility = View.VISIBLE
            }
        }
    }
}

private class AnimeDiffCallback : DiffUtil.ItemCallback<ViewAnime>() {
    override fun areItemsTheSame(oldItem: ViewAnime, newItem: ViewAnime): Boolean {
        return oldItem.value == newItem.value
    }

    override fun areContentsTheSame(oldItem: ViewAnime, newItem: ViewAnime): Boolean {
        return oldItem.value == newItem.value
                && oldItem.isFavorite == newItem.isFavorite
    }
}