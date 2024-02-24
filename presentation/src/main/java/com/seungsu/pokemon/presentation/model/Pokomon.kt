package com.seungsu.pokemon.presentation.model

import com.seungsu.pokemon.domain.model.PokemonEntity

data class Pokemon(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val detail: String
)

fun PokemonEntity.toUiModel(): Pokemon {
    return Pokemon(
        id = id,
        imageUrl = imageUrl,
        name = name,
        detail = detail
    )
}
