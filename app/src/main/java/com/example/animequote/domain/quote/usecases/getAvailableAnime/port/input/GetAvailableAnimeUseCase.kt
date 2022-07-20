package com.example.animequote.domain.quote.usecases.getAvailableAnime.port.input

import com.example.animequote.domain.quote.entity.AnimeTitle
import kotlinx.coroutines.flow.Flow

interface GetAvailableAnimeUseCase {
    suspend fun execute(): Flow<List<AnimeTitle>>
}