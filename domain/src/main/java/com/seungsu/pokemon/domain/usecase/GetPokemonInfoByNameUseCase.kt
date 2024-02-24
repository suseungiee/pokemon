package com.seungsu.pokemon.domain.usecase

import com.seungsu.pokemon.domain.annotation.IoDispatcher
import com.seungsu.pokemon.domain.base.FlowUseCase
import com.seungsu.pokemon.domain.model.PokemonEntity
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPokemonInfoByNameUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<String, PokemonEntity>(ioDispatcher) {
    override fun execute(params: String): Flow<PokemonEntity> = flow {
        emit(pokemonRepository.getPokemonInfoByName(params))
    }
}
