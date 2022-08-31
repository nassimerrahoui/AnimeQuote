package com.belt.animequote.application.getQuotesByAnimeTitle

import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.domain.entity.Quote
import com.belt.animequote.domain.port.input.GetQuotesByAnimeTitleUseCase
import com.belt.animequote.domain.port.output.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetQuotesByAnimeTitleInteractor @Inject constructor(
    private val quoteRepository: QuoteRepository
    ) : GetQuotesByAnimeTitleUseCase {

    override suspend fun execute(animeTitle: AnimeTitle): Flow<List<Quote>> =
        flow {
            emit(quoteRepository.findByAnimeTitle(animeTitle))
        }
}