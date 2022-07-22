package com.example.animequote.domain.quote.usecases.getQuotesByAnimeTitle.port.output

import com.example.animequote.domain.quote.entity.AnimeTitle
import com.example.animequote.domain.quote.entity.Quote

interface GetQuotesByAnimeTitlePort {
    suspend fun execute(animeTitle: AnimeTitle): List<Quote>
}