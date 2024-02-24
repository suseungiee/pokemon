package com.seungsu.pokemon.domain.usecase

import com.seungsu.pokemon.domain.annotation.IoDispatcher
import com.seungsu.pokemon.domain.base.FlowUseCase
import com.seungsu.pokemon.domain.model.PokemonEntity
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPokemonInfoByIdUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
): FlowUseCase<Int, PokemonEntity>(ioDispatcher) {
    override fun execute(params: Int): Flow<PokemonEntity> = flow {
        emit(pokemonRepository.getPokemonInfoById(params))
    }
}
