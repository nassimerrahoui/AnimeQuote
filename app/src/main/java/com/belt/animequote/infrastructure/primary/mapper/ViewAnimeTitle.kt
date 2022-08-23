package com.belt.animequote.infrastructure.primary.mapper

import com.belt.animequote.domain.entity.AnimeTitle
import java.io.Serializable

data class ViewAnimeTitle(val value: String) : Serializable {
    fun toAnimeTitle() = AnimeTitle(value)
}