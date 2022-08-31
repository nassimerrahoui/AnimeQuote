package com.belt.animequote.domain.entity

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class AnimeTitleTest {
    @Test
    fun `should create anime title from string`() {
        val animeTitle = AnimeTitle("anime")
        assertThat(animeTitle.value).isEqualTo("anime")
    }

    @Test
    fun `should throw when empty value`() {
        assertThatThrownBy { AnimeTitle("") }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("value can't be empty")
    }
}