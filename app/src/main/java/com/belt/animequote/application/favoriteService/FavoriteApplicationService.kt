package com.belt.animequote.application.favoriteService

import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.domain.port.input.FavoriteService
import com.belt.animequote.domain.port.output.FavoriteAnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteApplicationService @Inject constructor(
    private val favoriteAnimeRepository: FavoriteAnimeRepository
    ): FavoriteService {

    override suspend fun getAllFavoriteAnime(): Flow<Set<AnimeTitle>> = flow { emit(favoriteAnimeRepository.findAll().toSet()) }

    override suspend fun addAnime(animeTitle: AnimeTitle) {
        favoriteAnimeRepository.add(animeTitle)
    }

    override suspend fun removeAnime(animeTitle: AnimeTitle) {
        favoriteAnimeRepository.remove(animeTitle)
    }
}