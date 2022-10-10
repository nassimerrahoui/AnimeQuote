package com.belt.animequote.domain.port.input

import com.belt.animequote.domain.entity.Anime
import kotlinx.coroutines.flow.Flow

interface GetAvailableAnimeUseCase {
    suspend fun execute(): Flow<List<Anime>>
}