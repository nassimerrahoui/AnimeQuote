package com.belt.animequote.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.domain.port.input.GetAvailableAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvailableAnimeViewModel @Inject constructor(
    private val getAvailableAnimeUseCase: GetAvailableAnimeUseCase,
) : ViewModel() {
    val availableAnime = MutableLiveData<List<AnimeTitle>>()

    fun loadAvailableAnime() = viewModelScope.launch {
        getAvailableAnimeUseCase.execute().collect(availableAnime::postValue)
    }
}