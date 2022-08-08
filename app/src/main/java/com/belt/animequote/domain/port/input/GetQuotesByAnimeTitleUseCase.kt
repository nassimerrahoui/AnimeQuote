package com.belt.animequote.domain.port.input

import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.domain.entity.Quote
import kotlinx.coroutines.flow.Flow

interface GetQuotesByAnimeTitleUseCase {
    suspend fun execute(animeTitle: AnimeTitle): Flow<List<Quote>>
}