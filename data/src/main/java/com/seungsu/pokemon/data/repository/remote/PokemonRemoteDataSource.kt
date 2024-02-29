package com.seungsu.pokemon.data.repository.remote

import com.seungsu.pokemon.domain.model.PokemonEntity
import com.seungsu.pokemon.domain.model.SimplePokemonEntity

internal interface PokemonRemoteDataSource {
    suspend fun getPokemonInfoByName(name: String): PokemonEntity

    suspend fun getPokemons(offset: Int, pageSize: Int): List<SimplePokemonEntity>
}
