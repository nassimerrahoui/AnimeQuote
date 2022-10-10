package com.belt.animequote.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.belt.animequote.R
import com.belt.animequote.databinding.FragmentFavoritesBinding
import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.infrastructure.primary.mapper.ViewAnime
import com.belt.animequote.ui.adapter.FavoritesAnimeTitleAdapter
import com.belt.animequote.ui.decorator.AnimeTitleItemDecorator
import com.belt.animequote.ui.viewmodel.FavoriteAnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), FavoritesAnimeTitleAdapter.FavoriteAnimeTitleViewHolder.OnAnimeTitleClickListener {
    private val favoriteAnimeViewModel: FavoriteAnimeViewModel by viewModels()
    private val favoritesAnimeTitleAdapter: FavoritesAnimeTitleAdapter by lazy { FavoritesAnimeTitleAdapter(this) }
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoritesAnimeTitleRecyclerView.apply {
            adapter = favoritesAnimeTitleAdapter
            layoutManager = LinearLayoutManager(context)
            this.addItemDecoration(AnimeTitleItemDecorator(resources.getDimensionPixelSize(R.dimen.card_anime_title_margin)))
        }
        favoriteAnimeViewModel.loadFavoritesAnime()
        favoriteAnimeViewModel.favoriteAnimes.observe(viewLifecycleOwner, onNewAnimeTitle)
    }

    private val onNewAnimeTitle = Observer<List<AnimeTitle>>{
        favoritesAnimeTitleAdapter.submitList(it)
    }

    override fun onClick(viewAnime: ViewAnime) {
        FavoritesFragmentDirections
            .actionFavoritesFragmentToQuotesByAnimeTitleFragment(viewAnime)
            .let { findNavController().navigate(it) }
    }

    override fun onRemoveFavoriteIconClick(viewAnime: ViewAnime) {
        favoriteAnimeViewModel.removeAnimeFromFavorites(AnimeTitle(viewAnime.value))
    }
}