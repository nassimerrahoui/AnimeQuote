package com.example.animequote.infrastucture

import com.example.animequote.domain.quote.entity.AnimeTitle
import com.example.animequote.domain.quote.usecases.getAvailableAnime.port.output.GetAvailableAnimePort
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAvailableAnimeAdapter @Inject constructor(
    private val animeQuoteApi: AnimeQuoteApi,
) : GetAvailableAnimePort {
    override suspend fun execute(): List<AnimeTitle> = withContext(Dispatchers.IO) {
        animeQuoteApi
            .getAvailableAnime()
            .filter { it.isNotEmpty() }
            .sorted()
            .map(::AnimeTitle)
    }
}