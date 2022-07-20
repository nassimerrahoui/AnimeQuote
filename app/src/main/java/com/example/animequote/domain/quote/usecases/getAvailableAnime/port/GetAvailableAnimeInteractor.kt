package com.example.animequote.domain.quote.usecases.getAvailableAnime.port

import com.example.animequote.domain.quote.entity.AnimeTitle
import com.example.animequote.domain.quote.usecases.getAvailableAnime.port.input.GetAvailableAnimeUseCase
import com.example.animequote.domain.quote.usecases.getAvailableAnime.port.output.GetAvailableAnimePort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAvailableAnimeInteractor @Inject constructor(
    private val getAvailableAnimePort: GetAvailableAnimePort
) : GetAvailableAnimeUseCase {
    override suspend fun execute(): Flow<List<AnimeTitle>> =
        flow {
            emit(getAvailableAnimePort.execute())
        }
}