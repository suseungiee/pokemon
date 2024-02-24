package com.seungsu.pokemon.domain.usecase

import com.seungsu.pokemon.domain.model.PokemonEntity

interface PokemonRepository {
    suspend fun getPokemonInfoById(id: Int): PokemonEntity

    suspend fun getPokemonInfoByName(name: String): PokemonEntity

    suspend fun getPokemons(offset: Int, pageSize: Int): List<PokemonEntity>
}
