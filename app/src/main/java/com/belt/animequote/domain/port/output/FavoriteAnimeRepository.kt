package com.belt.animequote.domain.port.output

import com.belt.animequote.domain.entity.AnimeTitle

interface FavoriteAnimeRepository {
    suspend fun findAll(): List<AnimeTitle>
    suspend fun add(animeTitle: AnimeTitle)
    suspend fun remove(animeTitle: AnimeTitle)
}
