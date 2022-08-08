package com.belt.animequote.domain.port.output

import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.domain.entity.Quote

interface QuoteRepository {
    suspend fun findAllAnimeTitle(): List<AnimeTitle>
    suspend fun findByAnimeTitle(animeTitle: AnimeTitle): List<Quote>
}