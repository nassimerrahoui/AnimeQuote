package com.example.animequote.infrastucture

import com.example.animequote.domain.quote.entity.AnimeTitle
import com.example.animequote.domain.quote.entity.Quote
import com.example.animequote.domain.quote.usecases.getQuotesByAnimeTitle.port.output.GetQuotesByAnimeTitlePort
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetQuotesByAnimeTitleAdapter @Inject constructor(
    private val animeQuoteApi: AnimeQuoteApi,
) : GetQuotesByAnimeTitlePort {
    override suspend fun execute(animeTitle: AnimeTitle): List<Quote> = withContext(Dispatchers.IO) {
        animeQuoteApi
            .getQuotesByAnimeTitle(animeTitle.value)
            .map { it.toQuote() }
    }
}