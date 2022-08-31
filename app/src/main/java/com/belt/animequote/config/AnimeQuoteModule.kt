package com.belt.animequote.config

import android.content.Context
import androidx.room.Room
import com.belt.animequote.application.favoriteService.FavoriteApplicationService
import com.belt.animequote.application.getAvailableAnime.GetAvailableAnimeInteractor
import com.belt.animequote.domain.port.input.GetAvailableAnimeUseCase
import com.belt.animequote.domain.port.output.QuoteRepository
import com.belt.animequote.application.getQuotesByAnimeTitle.GetQuotesByAnimeTitleInteractor
import com.belt.animequote.domain.port.input.FavoriteService
import com.belt.animequote.domain.port.input.GetQuotesByAnimeTitleUseCase
import com.belt.animequote.domain.port.output.FavoriteAnimeRepository
import com.belt.animequote.infrastructure.secondary.client.AnimeQuoteApiClient
import com.belt.animequote.infrastructure.secondary.adapter.AnimeQuoteApiAdapter
import com.belt.animequote.infrastructure.secondary.adapter.FavoriteAnimeRepositoryAdapter
import com.belt.animequote.infrastructure.secondary.adapter.room.dao.FavoriteAnimeDao
import com.belt.animequote.infrastructure.secondary.client.RoomDatabaseClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AnimeQuoteModule {
    @Binds
    abstract fun bindsQuoteRepository(animeQuoteApiAdapter: AnimeQuoteApiAdapter): QuoteRepository

    @Binds
    abstract fun bindsGetAvailableAnimeUseCase(getAvailableAnimeInteractor: GetAvailableAnimeInteractor): GetAvailableAnimeUseCase

    @Binds
    abstract fun bindsGetQuotesByAnimeTitleUseCase(getQuotesByAnimeTitleInteractor: GetQuotesByAnimeTitleInteractor): GetQuotesByAnimeTitleUseCase

    @Binds
    abstract fun bindsFavoriteAnimeRepository(favoriteAnimeRepositoryAdapter: FavoriteAnimeRepositoryAdapter): FavoriteAnimeRepository

    @Binds
    abstract fun bindsFavoriteService(favoriteApplicationService: FavoriteApplicationService): FavoriteService

    companion object {
        private const val BASE_URL = "https://animechan.vercel.app/api/"
        private const val DATABASE_NAME = "AnimeQuote"

        @Provides
        @Singleton
        fun provideAnimeQuoteApi(): AnimeQuoteApiClient {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(AnimeQuoteApiClient::class.java)
        }

        @Provides
        @Singleton
        fun provideRoomDatabase(@ApplicationContext applicationContext: Context): RoomDatabaseClient {
            return Room.databaseBuilder(
                applicationContext,
                RoomDatabaseClient::class.java,
                DATABASE_NAME,
            ).build()
        }

        @Provides
        @Singleton
        fun provideFavoriteAnimeDao(roomDatabaseClient: RoomDatabaseClient): FavoriteAnimeDao {
            return roomDatabaseClient.favoriteAnimeDao()
        }
    }
}