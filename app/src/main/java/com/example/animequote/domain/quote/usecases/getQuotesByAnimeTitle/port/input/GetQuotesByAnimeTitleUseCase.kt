package com.example.animequote.domain.quote.usecases.getQuotesByAnimeTitle.port.input

import com.example.animequote.domain.quote.entity.AnimeTitle
import com.example.animequote.domain.quote.entity.Quote
import kotlinx.coroutines.flow.Flow

interface GetQuotesByAnimeTitleUseCase {
    suspend fun execute(animeTitle: AnimeTitle): Flow<List<Quote>>
}