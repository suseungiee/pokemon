package com.seungsu.pokemon.data.repository.remote

import com.seungsu.pokemon.domain.model.PokemonEntity

internal interface PokemonRemoteDataSource {
    suspend fun getPokemonInfoById(id: Int): PokemonEntity

    suspend fun getPokemonInfoByName(name: String): PokemonEntity

    suspend fun getPokemons(offset: Int, pageSize: Int): List<PokemonEntity>
}
