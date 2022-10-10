package com.belt.animequote.domain.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AnimeTest {
    @Test
    fun `should build anime with defaults`() {
        val anime = Anime(AnimeTitle("anime"))

        assertThat(anime.animeTitle.value).isEqualTo("anime")
    }

    @Test
    fun `should build anime`() {
        val anime = Anime(
            animeTitle = AnimeTitle("anime"),
            isFavorite = true,
        )

        assertThat(anime.animeTitle.value).isEqualTo("anime")
        assertThat(anime.isFavorite).isTrue
    }
}