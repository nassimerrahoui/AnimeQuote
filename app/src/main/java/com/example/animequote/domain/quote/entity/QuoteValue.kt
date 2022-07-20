package com.example.animequote.domain.quote.entity

@JvmInline
value class QuoteValue(private val value: String) {
    init {
        if (value.isEmpty()) {
            throw IllegalArgumentException("value can't be empty")
        }
    }
}