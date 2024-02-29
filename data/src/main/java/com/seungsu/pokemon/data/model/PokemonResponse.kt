package com.seungsu.pokemon.data.model

import com.seungsu.pokemon.domain.model.PokemonEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonResponse(
    @SerialName("name") val name: String,
    @SerialName("id") val id: Int,
    @SerialName("weight") val weight: Int,
    @SerialName("height") val height: Int,
    @SerialName("types") val types: List<PokemonTypeResponse>
)

@Serializable
internal data class PokemonTypeResponse(
    @SerialName("type") val type: SimpleTypeResponse
)

@Serializable
internal data class SimpleTypeResponse(
    @SerialName("name") val typeName: String
)

internal fun PokemonResponse.toDomain(): PokemonEntity {
    return PokemonEntity(
        id = id,
        name = name,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
        weight = weight,
        height = height,
        types = types.map { it.type.typeName }
    )
}
