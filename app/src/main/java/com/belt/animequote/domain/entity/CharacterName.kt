package com.belt.animequote.domain.entity

@JvmInline
value class CharacterName(val value: String) {
    init {
        if (value.isEmpty()) {
            throw IllegalArgumentException("value can't be empty")
        }
    }
}