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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AnimeQuoteModule::class]
)
abstract class FakeAnimeQuoteModule {
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
        @Provides
        @Singleton
        fun provideAnimeQuoteApi(): AnimeQuoteApiClient {
            return FakeQuoteApiClient()
        }

        @Provides
        @Singleton
        fun provideRoomDatabase(@ApplicationContext applicationContext: Context): RoomDatabaseClient {
            return Room.inMemoryDatabaseBuilder(
                applicationContext,
                RoomDatabaseClient::class.java,
            ).build()
        }

        @Provides
        @Singleton
        fun provideFavoriteAnimeDao(roomDatabaseClient: RoomDatabaseClient): FavoriteAnimeDao {
            return roomDatabaseClient.favoriteAnimeDao()
        }
    }
}