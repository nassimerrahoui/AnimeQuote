package com.belt.animequote.infrastructure.secondary.adapter

import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.domain.port.output.FavoriteAnimeRepository
import com.belt.animequote.infrastructure.secondary.adapter.room.dao.FavoriteAnimeDao
import com.belt.animequote.infrastructure.secondary.adapter.room.entity.FavoriteAnimeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteAnimeRepositoryAdapter @Inject constructor(
    private val favoriteAnimeDao: FavoriteAnimeDao
    ): FavoriteAnimeRepository {

    override suspend fun findAll(): List<AnimeTitle> = withContext(Dispatchers.IO) {
        favoriteAnimeDao
        .findAll()
            .map { it.title }
            .sorted()
            .map { AnimeTitle(it) }
    }

    override suspend fun add(animeTitle: AnimeTitle) = withContext(Dispatchers.IO) {
        favoriteAnimeDao.add(FavoriteAnimeEntity(animeTitle.value))
    }

    override suspend fun remove(animeTitle: AnimeTitle) = withContext(Dispatchers.IO) {
       favoriteAnimeDao.remove(FavoriteAnimeEntity(animeTitle.value))
    }
}