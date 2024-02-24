package com.seungsu.pokemon.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Params, Type>(private val dispatcher: CoroutineDispatcher) {
    operator fun invoke(params: Params): Flow<Type> {
        return execute(params).flowOn(dispatcher)
    }

    abstract fun execute(params: Params): Flow<Type>
}
