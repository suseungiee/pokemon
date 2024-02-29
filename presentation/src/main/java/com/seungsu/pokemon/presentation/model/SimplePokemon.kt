package com.seungsu.pokemon.presentation.model

import com.seungsu.pokemon.domain.model.SimplePokemonEntity

data class SimplePokemon(
    val id: Int,
    val name: String,
    val imageUrl: String
)

fun SimplePokemonEntity.toUiModel(): SimplePokemon {
    return SimplePokemon(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}
