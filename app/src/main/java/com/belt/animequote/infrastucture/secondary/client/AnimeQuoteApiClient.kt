package com.belt.animequote.infrastucture.secondary.client

import com.belt.animequote.infrastucture.primary.RestQuote
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeQuoteApiClient {
    @GET("available/anime")
    suspend fun getAvailableAnime(): List<String>

    @GET("quotes/anime?")
    suspend fun getQuotesByAnimeTitle(@Query("title") title: String): List<RestQuote>
}