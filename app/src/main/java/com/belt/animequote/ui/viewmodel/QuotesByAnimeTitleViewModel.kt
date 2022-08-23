package com.belt.animequote.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.domain.entity.Quote
import com.belt.animequote.domain.port.input.GetQuotesByAnimeTitleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesByAnimeTitleViewModel @Inject constructor(
    private val getQuotesByAnimeTitleUseCase: GetQuotesByAnimeTitleUseCase,
) : ViewModel() {
    val quotesByAnimeTitle = MutableLiveData<List<Quote>>()

    fun loadQuotesByAnimeTitle(animeTitle: AnimeTitle) = viewModelScope.launch {
        getQuotesByAnimeTitleUseCase.execute(animeTitle).collect(quotesByAnimeTitle::postValue)
    }
}