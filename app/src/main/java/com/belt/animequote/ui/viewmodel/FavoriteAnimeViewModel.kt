package com.belt.animequote.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.domain.port.input.FavoriteService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteAnimeViewModel @Inject constructor(
    private val favoriteService: FavoriteService,
) : ViewModel() {
    val favoriteAnimes = MutableLiveData<List<AnimeTitle>>()

    fun loadFavoritesAnime() = viewModelScope.launch {
        favoriteService.getAllFavoriteAnime().collect(favoriteAnimes::postValue)
    }

    fun removeAnimeFromFavorites(animeTitle: AnimeTitle) = viewModelScope.launch {
        favoriteService.removeAnime(animeTitle)
        loadFavoritesAnime()
    }
}