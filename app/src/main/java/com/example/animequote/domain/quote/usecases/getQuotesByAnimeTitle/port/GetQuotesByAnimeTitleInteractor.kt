package com.example.animequote.domain.quote.usecases.getQuotesByAnimeTitle.port

import com.example.animequote.domain.quote.entity.AnimeTitle
import com.example.animequote.domain.quote.entity.Quote
import com.example.animequote.domain.quote.usecases.getQuotesByAnimeTitle.port.input.GetQuotesByAnimeTitleUseCase
import com.example.animequote.domain.quote.usecases.getQuotesByAnimeTitle.port.output.GetQuotesByAnimeTitlePort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetQuotesByAnimeTitleInteractor @Inject constructor(
    private val getQuotesByAnimeTitlePort: GetQuotesByAnimeTitlePort
) : GetQuotesByAnimeTitleUseCase {
    override suspend fun execute(animeTitle: AnimeTitle): Flow<List<Quote>> =
        flow {
            emit(getQuotesByAnimeTitlePort.execute(animeTitle))
        }
}