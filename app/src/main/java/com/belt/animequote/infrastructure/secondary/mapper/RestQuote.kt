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
    val text: String,
) {
    fun toQuote(): Quote = Quote(
        animeTitle = AnimeTitle(animeTitle),
        characterName = CharacterName(characterName),
        text = QuoteText(text),
    )
}