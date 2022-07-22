package com.example.animequote.infrastucture

import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeQuoteApi {
    @GET("available/anime")
    suspend fun getAvailableAnime(): List<String>

    @GET("quotes/anime?")
    suspend fun getQuotesByAnimeTitle(@Query("title") title: String): List<RestQuote>
}