package com.example.animequote.domain.quote.entity

@JvmInline
value class AnimeTitle(private val value: String) {
    init {
        if (value.isEmpty()) {
            throw IllegalArgumentException("value can't be empty")
        }
    }
}