package com.belt.animequote.infrastructure.secondary.adapter.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.belt.animequote.infrastructure.secondary.adapter.room.entity.FavoriteAnimeEntity

@Dao
interface FavoriteAnimeDao {
    @Query("SELECT * FROM FavoriteAnime")
    fun findAll(): List<FavoriteAnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(favoriteAnime: FavoriteAnimeEntity)

    @Delete
    fun remove(favoriteAnime: FavoriteAnimeEntity)
}