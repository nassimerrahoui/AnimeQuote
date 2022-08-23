package com.belt.animequote.infrastructure.secondary.mapper

import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.domain.entity.CharacterName
import com.belt.animequote.domain.entity.Quote
import com.belt.animequote.domain.entity.QuoteText
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class RestQuote(
    @JsonProperty("anime")
    val animeTitle: String,
    @JsonProperty("character")
    val characterName: String,
    @JsonProperty("quote")
    val quoteText: String,
) {
    fun toQuote(): Quote {
        val animeTitle = AnimeTitle(animeTitle)
        val characterName = CharacterName(characterName)
        val quoteText = QuoteText(quoteText)
        return Quote(animeTitle, characterName, quoteText)
    }
}