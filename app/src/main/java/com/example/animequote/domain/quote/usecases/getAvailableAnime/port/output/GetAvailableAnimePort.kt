package com.example.animequote.domain.quote.usecases.getAvailableAnime.port.output

import com.example.animequote.domain.quote.entity.AnimeTitle

interface GetAvailableAnimePort {
    suspend fun execute(): List<AnimeTitle>
}