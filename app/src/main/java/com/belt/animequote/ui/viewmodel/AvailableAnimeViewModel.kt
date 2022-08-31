package com.belt.animequote.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.domain.port.input.FavoriteService
import com.belt.animequote.domain.port.input.GetAvailableAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvailableAnimeViewModel @Inject constructor(
    private val getAvailableAnimeUseCase: GetAvailableAnimeUseCase,
    private val favoriteService: FavoriteService,
) : ViewModel() {
    val availableAnime = MutableLiveData<List<AnimeTitle>>()

    fun loadAvailableAnime() = viewModelScope.launch {
        getAvailableAnimeUseCase.execute().collect(availableAnime::postValue)
    }

    fun addAnimeToFavorite(animeTitle: AnimeTitle) = viewModelScope.launch {
        favoriteService.addAnime(animeTitle)
    }
}