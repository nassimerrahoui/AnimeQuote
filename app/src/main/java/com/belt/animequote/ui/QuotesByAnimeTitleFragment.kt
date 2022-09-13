package com.belt.animequote.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.belt.animequote.domain.entity.Quote
import com.belt.animequote.ui.adapter.QuotesByAnimeTitleAdapter
import com.belt.animequote.ui.viewmodel.QuotesByAnimeTitleViewModel
import com.belt.animequote.databinding.FragmentQuotesByAnimeTitleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuotesByAnimeTitleFragment : Fragment() {
    private val quotesByAnimeTitleViewModel: QuotesByAnimeTitleViewModel by viewModels()
    private val quotesByAnimeTitleAdapter: QuotesByAnimeTitleAdapter by lazy { QuotesByAnimeTitleAdapter() }
    private lateinit var binding: FragmentQuotesByAnimeTitleBinding
    private val args: QuotesByAnimeTitleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuotesByAnimeTitleBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.quotesByAnimePageTitle.text = args.animeTitle.value
        initQuotesRecyclerView()
    }

    private fun initQuotesRecyclerView() {
        binding.quotesByAnimeTitleRecyclerView.apply {
            adapter = quotesByAnimeTitleAdapter
            layoutManager = LinearLayoutManager(context)
        }

        quotesByAnimeTitleViewModel.loadQuotesByAnimeTitle(args.animeTitle.toAnimeTitle())
        quotesByAnimeTitleViewModel.quotesByAnimeTitle.observe(viewLifecycleOwner, onNewQuote)
    }

    private val onNewQuote = Observer<List<Quote>> {
        quotesByAnimeTitleAdapter.submitList(it)
    }
}