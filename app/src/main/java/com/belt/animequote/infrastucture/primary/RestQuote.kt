package com.belt.animequote.infrastucture.primary

import com.belt.animequote.domain.entity.AnimeTitle
import com.belt.animequote.domain.entity.CharacterName
import com.belt.animequote.domain.entity.Quote
import com.belt.animequote.domain.entity.QuoteValue

data class RestQuote(val id: AnimeTitle, val name: CharacterName, val value: QuoteValue) {
    fun toQuote() = Quote(id, name, value)
}