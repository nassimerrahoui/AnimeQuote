package com.belt.animequote.domain.entity

@JvmInline
value class AnimeTitle(val value: String) {
    init {
        if (value.isEmpty()) {
            throw IllegalArgumentException("value can't be empty")
        }
    }
}