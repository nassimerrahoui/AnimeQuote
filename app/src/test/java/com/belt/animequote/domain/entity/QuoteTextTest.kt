package com.belt.animequote.domain.entity

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class QuoteTextTest {
    @Test
    fun `should create quote text from string`() {
        val quoteText = QuoteText("A quote text.")
        assertThat(quoteText.value).isEqualTo("A quote text.")
    }

    @Test
    fun `should throw when empty value`() {
        assertThatThrownBy { QuoteText("") }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("value can't be empty")
    }
}