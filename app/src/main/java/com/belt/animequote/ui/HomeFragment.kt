package com.belt.animequote.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.belt.animequote.R
import com.belt.animequote.databinding.FragmentHomeBinding
import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.infrastructure.primary.mapper.ViewAnime
import com.belt.animequote.ui.adapter.AnimeAdapter
import com.belt.animequote.ui.decorator.AnimeTitleItemDecorator
import com.belt.animequote.ui.viewmodel.AvailableAnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), AnimeAdapter.AnimeViewHolder.OnAnimeClickListener {
    private val availableAnimeViewModel: AvailableAnimeViewModel by viewModels()
    private val animeAdapter: AnimeAdapter by lazy { AnimeAdapter(this) }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.animeRecyclerView.apply {
            adapter = animeAdapter
            layoutManager = LinearLayoutManager(context)
            this.addItemDecoration(AnimeTitleItemDecorator(resources.getDimensionPixelSize(R.dimen.card_anime_title_margin)))
        }
        availableAnimeViewModel.loadAvailableAnime()
        availableAnimeViewModel.availableAnime.observe(viewLifecycleOwner, onNewViewAnimeList)
        initSearchAnimeListener()
    }

    private val onNewViewAnimeList = Observer<List<ViewAnime>>{
        animeAdapter.submitList(it)
    }

    private fun initSearchAnimeListener() {
        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
           animeAdapter.filter.filter(text)
        }
    }

    override fun onClick(viewAnime: ViewAnime) {
        HomeFragmentDirections
            .actionHomeFragmentToQuotesByAnimeTitleFragment(viewAnime)
            .let { findNavController().navigate(it) }
    }

    override fun onFavoriteIconClick(animeTitle: AnimeTitle) {
        availableAnimeViewModel.addAnimeToFavorite(animeTitle)
    }
}