package com.belt.animequote.config

import com.belt.animequote.infrastructure.secondary.client.AnimeQuoteApiClient
import com.belt.animequote.infrastructure.secondary.mapper.RestQuote

class FakeQuoteApiClient : AnimeQuoteApiClient {
    override suspend fun getAvailableAnime(): List<String> {
        return listOf("AnimeOne", "AnimeTwo")
    }

    override suspend fun getQuotesByAnimeTitle(title: String): List<RestQuote> {
        return listOf(RestQuote(title, "RandomCharacter", "Hello !"))
    }
}