package com.belt.animequote.application.getAvailableAnime

import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.domain.port.input.GetAvailableAnimeUseCase
import com.belt.animequote.domain.port.output.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAvailableAnimeInteractor @Inject constructor(
    private val quoteRepository: QuoteRepository
    ) : GetAvailableAnimeUseCase {

    override suspend fun execute(): Flow<List<AnimeTitle>> =
        flow {
            emit(quoteRepository.findAllAnimeTitle())
        }
}