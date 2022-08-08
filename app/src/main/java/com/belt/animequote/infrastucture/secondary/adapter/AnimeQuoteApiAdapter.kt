package com.belt.animequote.infrastucture.secondary.adapter

import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.domain.entity.Quote
import com.belt.animequote.domain.port.output.QuoteRepository
import com.belt.animequote.infrastucture.secondary.client.AnimeQuoteApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnimeQuoteApiAdapter @Inject constructor(
    private val animeQuoteApi: AnimeQuoteApiClient,
) : QuoteRepository {

    override suspend fun findAllAnimeTitle(): List<AnimeTitle> = withContext(Dispatchers.IO) {
        animeQuoteApi
            .getAvailableAnime()
            .filter { it.isNotEmpty() }
            .sorted()
            .map(::AnimeTitle)
    }

    override suspend fun findByAnimeTitle(animeTitle: AnimeTitle): List<Quote> = withContext(Dispatchers.IO) {
        animeQuoteApi
            .getQuotesByAnimeTitle(animeTitle.value)
            .map { it.toQuote() }
    }
}