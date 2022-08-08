package com.belt.animequote.config

import com.belt.animequote.application.getAvailableAnime.GetAvailableAnimeInteractor
import com.belt.animequote.domain.port.input.GetAvailableAnimeUseCase
import com.belt.animequote.domain.port.output.QuoteRepository
import com.belt.animequote.application.getQuotesByAnimeTitle.GetQuotesByAnimeTitleInteractor
import com.belt.animequote.domain.port.input.GetQuotesByAnimeTitleUseCase
import com.belt.animequote.infrastucture.secondary.client.AnimeQuoteApiClient
import com.belt.animequote.infrastucture.secondary.adapter.AnimeQuoteApiAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AnimeQuoteModule {
    @Binds
    abstract fun bindsQuoteRepository(getAnimeQuoteApiAdapter: AnimeQuoteApiAdapter): QuoteRepository

    @Binds
    abstract fun bindsGetAvailableAnimeUseCase(getAvailableAnimeInteractor: GetAvailableAnimeInteractor): GetAvailableAnimeUseCase

    @Binds
    abstract fun bindsGetQuotesByAnimeTitleUseCase(getQuotesByAnimeTitleInteractor: GetQuotesByAnimeTitleInteractor): GetQuotesByAnimeTitleUseCase

    companion object {
        @Provides
        @Singleton
        fun provideAnimeQuoteApi(): AnimeQuoteApiClient {
            return Retrofit.Builder()
                .baseUrl("https://animechan.vercel.app/api/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(AnimeQuoteApiClient::class.java)
        }
    }
}