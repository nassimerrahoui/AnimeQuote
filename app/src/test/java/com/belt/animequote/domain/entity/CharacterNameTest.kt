package com.belt.animequote.domain.entity

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class CharacterNameTest {
    @Test
    fun `should create character name from string`() {
        val characterName = CharacterName("name")
        assertThat(characterName.value).isEqualTo("name")
    }

    @Test
    fun `should throw when empty value`() {
        assertThatThrownBy { CharacterName("") }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("value can't be empty")
    }
}