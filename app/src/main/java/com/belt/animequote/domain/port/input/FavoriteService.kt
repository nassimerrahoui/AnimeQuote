package com.belt.animequote.domain.port.input

import com.belt.animequote.domain.entity.AnimeTitle
import kotlinx.coroutines.flow.Flow

interface FavoriteService {
    suspend fun getAllFavoriteAnime(): Flow<List<AnimeTitle>>
    suspend fun addAnime(animeTitle: AnimeTitle)
    suspend fun removeAnime(animeTitle: AnimeTitle)
}