package com.seungsu.pokemon.data.model

import com.seungsu.pokemon.domain.model.PokemonEntity
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

internal fun SimplePokemonResponse.toDomain(): PokemonEntity {
    val id = infoUrl.split("/")
        .dropLast(1)
        .last().toInt()
    return PokemonEntity(
        id = id,
        name = name,
        detail = "",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
    )
}
