package com.example.animequote.config

import com.example.animequote.domain.quote.usecases.getAvailableAnime.port.GetAvailableAnimeInteractor
import com.example.animequote.domain.quote.usecases.getAvailableAnime.port.input.GetAvailableAnimeUseCase
import com.example.animequote.domain.quote.usecases.getAvailableAnime.port.output.GetAvailableAnimePort
import com.example.animequote.infrastucture.AnimeQuoteApi
import com.example.animequote.infrastucture.GetAvailableAnimeAdapter
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
    abstract fun bindsGetAvailableAnimePort(getAvailableAnimeAdapter: GetAvailableAnimeAdapter): GetAvailableAnimePort

    @Binds
    abstract fun bindsGetAvailableAnimeUseCase(getAvailableAnimeInteractor: GetAvailableAnimeInteractor): GetAvailableAnimeUseCase

    companion object {
        @Provides
        @Singleton
        fun provideAnimeQuoteApi(): AnimeQuoteApi {
            return Retrofit.Builder()
                .baseUrl("https://animechan.vercel.app/api/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(AnimeQuoteApi::class.java)
        }
    }
}