package com.belt.animequote.infrastructure.secondary.client

import androidx.room.Database
import androidx.room.RoomDatabase
import com.belt.animequote.infrastructure.secondary.adapter.room.dao.FavoriteAnimeDao
import com.belt.animequote.infrastructure.secondary.adapter.room.entity.FavoriteAnimeEntity

@Database(entities = [FavoriteAnimeEntity::class], version = 1)
abstract class RoomDatabaseClient : RoomDatabase() {
    abstract fun favoriteAnimeDao(): FavoriteAnimeDao
}