package com.example.animequote.infrastucture

import retrofit2.http.GET

interface AnimeQuoteApi {
    @GET("available/anime")
    suspend fun getAvailableAnime(): List<String>
}