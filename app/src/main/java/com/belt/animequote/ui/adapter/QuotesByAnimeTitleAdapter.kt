package com.belt.animequote.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.belt.animequote.domain.entity.Quote
import com.belt.animequote.databinding.QuoteItemBinding

class QuotesByAnimeTitleAdapter : ListAdapter<Quote, QuotesByAnimeTitleAdapter.QuotesByAnimeTitleViewHolder>(
    QuotesByAnimeTitleDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesByAnimeTitleViewHolder {
        return QuotesByAnimeTitleViewHolder(
            QuoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QuotesByAnimeTitleViewHolder, position: Int) {
        val quote = getItem(position)
        if (quote != null) {
            holder.bind(quote)
        }
    }

    class QuotesByAnimeTitleViewHolder(private val binding: QuoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Quote) {
            binding.quoteCharacterName.text = item.characterName.value
            binding.quoteText.text = item.text.value
        }
    }
}

private class QuotesByAnimeTitleDiffCallback : DiffUtil.ItemCallback<Quote>() {
    override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
        return oldItem.animeTitle == newItem.animeTitle
                && oldItem.characterName == newItem.characterName
                && oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
        return oldItem.animeTitle == newItem.animeTitle
                && oldItem.characterName == newItem.characterName
                && oldItem.text == newItem.text
    }
}