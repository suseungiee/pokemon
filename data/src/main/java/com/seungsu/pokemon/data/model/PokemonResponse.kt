package com.seungsu.pokemon.data.model

import com.seungsu.pokemon.domain.model.PokemonEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonResponse(
    @SerialName("name") val name: String,
    @SerialName("id") val id: Int,
    @SerialName("sprites") val sprities: SpritiesResponse
)

@Serializable
internal data class SpritiesResponse(
    @SerialName("front_default") val imageUrl: String
)

internal fun PokemonResponse.toDomain(): PokemonEntity {
    return PokemonEntity(
        id = id,
        name = name,
        detail = "",
        imageUrl = sprities.imageUrl
    )
}
