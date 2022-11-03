package com.belt.animequote.infrastructure.secondary.adapter

import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.domain.entity.Quote
import com.belt.animequote.domain.port.output.QuoteRepository
import com.belt.animequote.infrastructure.secondary.client.AnimeQuoteApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnimeQuoteApiAdapter @Inject constructor(
    private val animeQuoteApi: AnimeQuoteApiClient,
) : QuoteRepository {

    override suspend fun findAllAnimeTitle(): List<AnimeTitle> = withContext(Dispatchers.IO) {
        // TODO Temporary fix until the endpoint is available
        listOf(
            AnimeTitle("JoJo's Bizarre Adventure"),
            AnimeTitle("Fullmetal Alchemist: Brotherhood"),
            AnimeTitle("Great Teacher Onizuka"),
            AnimeTitle("One-Punch Man"),
            AnimeTitle("Kaiji"),
            AnimeTitle("Death Note"),
            AnimeTitle("Naruto"),
            AnimeTitle("Hunter X Hunter"),
        ).sortedBy(AnimeTitle::value)

       /* animeQuoteApi
            .getAvailableAnime()
            .filter { it.isNotEmpty() }
            .sorted()
            .map(::AnimeTitle)*/
    }

    override suspend fun findByAnimeTitle(animeTitle: AnimeTitle): List<Quote> = withContext(Dispatchers.IO) {
        animeQuoteApi
            .getQuotesByAnimeTitle(animeTitle.value)
            .map { it.toQuote() }
    }
}