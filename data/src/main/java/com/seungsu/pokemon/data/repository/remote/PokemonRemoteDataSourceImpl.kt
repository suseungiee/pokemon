package com.seungsu.pokemon.data.repository.remote

import com.seungsu.pokemon.data.api.PokemonApi
import com.seungsu.pokemon.data.model.toDomain
import com.seungsu.pokemon.domain.model.PokemonEntity
import javax.inject.Inject

internal class PokemonRemoteDataSourceImpl @Inject constructor(
    private val pokemonApi: PokemonApi
): PokemonRemoteDataSource {
    override suspend fun getPokemonInfoById(id: Int): PokemonEntity {
        return pokemonApi.getPokemonInfoById(id).toDomain()
    }

    override suspend fun getPokemonInfoByName(name: String): PokemonEntity {
        return pokemonApi.getPokemonInfoByName(name).toDomain()
    }

    override suspend fun getPokemons(offset: Int, pageSize: Int): List<PokemonEntity> {
        val pokemonUrls = pokemonApi.getPokemonNames(offset, pageSize).results
        val pokemons = mutableListOf<PokemonEntity>()
            pokemonUrls.map {
                pokemons.add(pokemonApi.getPokemonInfoByName(it.name).toDomain())
        }
        return pokemons
    }
}