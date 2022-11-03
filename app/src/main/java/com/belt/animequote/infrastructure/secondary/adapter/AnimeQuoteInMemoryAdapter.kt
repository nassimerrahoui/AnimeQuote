package com.belt.animequote.infrastructure.secondary.adapter

import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.domain.entity.CharacterName
import com.belt.animequote.domain.entity.Quote
import com.belt.animequote.domain.entity.QuoteText
import com.belt.animequote.domain.port.output.QuoteRepository

class AnimeQuoteInMemoryAdapter: QuoteRepository {
    override suspend fun findAllAnimeTitle(): List<AnimeTitle> {
        return listOf(
            AnimeTitle("AnimeOne"),
            AnimeTitle("AnimeTwo"),
        ).sortedBy(AnimeTitle::value)
    }

    override suspend fun findByAnimeTitle(animeTitle: AnimeTitle): List<Quote> {
        return listOf(
            Quote(
                animeTitle,
                CharacterName("RandomCharacter"),
                QuoteText("Hello !")
            )
        )
    }
}