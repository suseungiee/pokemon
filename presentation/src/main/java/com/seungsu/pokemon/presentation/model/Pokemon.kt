package com.seungsu.pokemon.presentation.model

import com.seungsu.pokemon.domain.model.PokemonEntity

data class Pokemon(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val weight: Int = 0,
    val height: Int = 0,
    val types: List<String> = emptyList()
)

fun PokemonEntity.toUiModel(): Pokemon {
    return Pokemon(
        id = id,
        imageUrl = imageUrl,
        name = name,
        weight = weight,
        height = height,
        types = types
    )
}
