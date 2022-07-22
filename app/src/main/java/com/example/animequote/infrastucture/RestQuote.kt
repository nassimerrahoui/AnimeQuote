package com.example.animequote.infrastucture

import com.example.animequote.domain.quote.entity.AnimeTitle
import com.example.animequote.domain.quote.entity.CharacterName
import com.example.animequote.domain.quote.entity.Quote
import com.example.animequote.domain.quote.entity.QuoteValue

data class RestQuote(val id: AnimeTitle, val name: CharacterName, val value: QuoteValue) {
    fun toQuote() = Quote(id, name, value)
}