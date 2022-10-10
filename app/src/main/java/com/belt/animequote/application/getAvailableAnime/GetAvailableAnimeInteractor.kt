package com.belt.animequote.application.getAvailableAnime

import com.belt.animequote.domain.entity.Anime
import com.belt.animequote.domain.port.input.GetAvailableAnimeUseCase
import com.belt.animequote.domain.port.output.FavoriteAnimeRepository
import com.belt.animequote.domain.port.output.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAvailableAnimeInteractor @Inject constructor(
    private val quoteRepository: QuoteRepository,
    private val favoriteAnimeRepository: FavoriteAnimeRepository,
    ) : GetAvailableAnimeUseCase {

    override suspend fun execute(): Flow<List<Anime>> {
        val favoriteAnimeTitleList = favoriteAnimeRepository.findAll()
        return flow {
            emit(
                quoteRepository
                    .findAllAnimeTitle()
                    .map { Anime(it, favoriteAnimeTitleList.contains(it)) }
            )
        }
    }
}