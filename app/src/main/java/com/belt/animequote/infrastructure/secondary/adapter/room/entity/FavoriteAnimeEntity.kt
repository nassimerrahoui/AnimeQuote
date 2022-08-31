package com.belt.animequote.infrastructure.secondary.adapter.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteAnime")
data class FavoriteAnimeEntity(
    @PrimaryKey val title: String
)