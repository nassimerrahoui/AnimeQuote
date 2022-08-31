package com.belt.animequote.domain.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class QuoteTest {
    @Test
    fun `should build quote`() {
        val quote = Quote(
                animeTitle = AnimeTitle("anime"),
                characterName = CharacterName("name"),
                text = QuoteText("A quote text."),
            )

        assertThat(quote.animeTitle.value).isEqualTo("anime")
        assertThat(quote.characterName.value).isEqualTo("name")
        assertThat(quote.text.value).isEqualTo("A quote text.")
    }
}