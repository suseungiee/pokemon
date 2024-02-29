package com.seungsu.pokemon.domain.usecase

import com.seungsu.pokemon.domain.model.PokemonEntity
import com.seungsu.pokemon.domain.model.SimplePokemonEntity

interface PokemonRepository {
    suspend fun getPokemonInfoByName(name: String): PokemonEntity

    suspend fun getPokemons(offset: Int, pageSize: Int): List<SimplePokemonEntity>
}
