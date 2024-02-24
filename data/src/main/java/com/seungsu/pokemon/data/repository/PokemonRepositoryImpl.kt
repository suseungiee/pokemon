package com.seungsu.pokemon.data.repository

import com.seungsu.pokemon.data.repository.remote.PokemonRemoteDataSource
import com.seungsu.pokemon.domain.model.PokemonEntity
import com.seungsu.pokemon.domain.usecase.PokemonRepository
import javax.inject.Inject

internal class PokemonRepositoryImpl @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
): PokemonRepository {
    override suspend fun getPokemonInfoById(id: Int): PokemonEntity {
        return pokemonRemoteDataSource.getPokemonInfoById(id)
    }

    override suspend fun getPokemonInfoByName(name: String): PokemonEntity {
        return pokemonRemoteDataSource.getPokemonInfoByName(name)
    }

    override suspend fun getPokemons(offset: Int, pageSize: Int): List<PokemonEntity> {
        return pokemonRemoteDataSource.getPokemons(offset, pageSize)
    }
}
