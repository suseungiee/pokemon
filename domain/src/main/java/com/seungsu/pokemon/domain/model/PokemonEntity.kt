package com.seungsu.pokemon.domain.model

data class PokemonEntity(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val weight: Int,
    val height: Int,
    val types: List<String>
)
