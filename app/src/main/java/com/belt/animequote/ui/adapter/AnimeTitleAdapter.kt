package com.belt.animequote.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.belt.animequote.databinding.AnimeTitleItemBinding
import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.infrastructure.primary.mapper.ViewAnimeTitle

class AnimeTitleAdapter(private val listener: AnimeTitleViewHolder.OnAnimeTitleClickListener) :
    ListAdapter<AnimeTitle, AnimeTitleAdapter.AnimeTitleViewHolder>(AnimeTitleDiffCallback()),
    Filterable {

    private var originalList: List<AnimeTitle> = listOf()

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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                return FilterResults().apply {
                    values = if (constraint.isNullOrEmpty())
                        originalList
                    else
                        originalList.filter { animeTitle -> animeTitle.value.contains(constraint, true) }
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                val filteredList = results?.values
                submitList(filteredList as List<AnimeTitle>, true)
            }
        }
    }

    override fun submitList(list: List<AnimeTitle>?) {
        submitList(list, false)
    }

    private fun submitList(list: List<AnimeTitle>?, filtered: Boolean) {
        if (!filtered) originalList = list ?: listOf()
        super.submitList(list)
    }

    class AnimeTitleViewHolder(private val binding: AnimeTitleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        interface OnAnimeTitleClickListener {
            fun onClick(viewAnimeTitle: ViewAnimeTitle)
            fun onFavoriteIconClick(viewAnimeTitle: ViewAnimeTitle)
        }

        fun bind(item: AnimeTitle, listener: OnAnimeTitleClickListener) {
            val viewAnimeTitle = ViewAnimeTitle(item.value)

            binding.animeTitle.text = item.value
            binding.root.setOnClickListener { listener.onClick(viewAnimeTitle) }
            binding.bookmarkBorderIcon.setOnClickListener { listener.onFavoriteIconClick(viewAnimeTitle) }
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