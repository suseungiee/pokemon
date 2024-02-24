package com.seungsu.pokemon.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonNamesResponse(
    @SerialName("results") val results: List<SimplePokemonResponse>
)

@Serializable
internal data class SimplePokemonResponse(
    @SerialName("name") val name: String,
    @SerialName("url") val infoUrl: String
)
