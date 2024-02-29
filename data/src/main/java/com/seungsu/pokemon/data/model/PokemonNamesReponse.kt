package com.seungsu.pokemon.data.model

import com.seungsu.pokemon.domain.model.SimplePokemonEntity
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

internal fun SimplePokemonResponse.toDomain(): SimplePokemonEntity {
    val id = infoUrl.split("/")
        .dropLast(1)
        .last().toInt()
    return SimplePokemonEntity(
        id = id,
        name = name,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    )
}
