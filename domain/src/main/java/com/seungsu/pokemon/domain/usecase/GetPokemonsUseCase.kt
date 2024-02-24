package com.seungsu.pokemon.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.seungsu.pokemon.domain.annotation.IoDispatcher
import com.seungsu.pokemon.domain.base.FlowUseCase
import com.seungsu.pokemon.domain.model.PokemonEntity
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetPokemonsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, PagingData<PokemonEntity>>(ioDispatcher) {
    override fun execute(params: Unit): Flow<PagingData<PokemonEntity>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = true)
    ) {
        object: PagingSource<Int, PokemonEntity>() {
            override fun getRefreshKey(state: PagingState<Int, PokemonEntity>): Int? = null

            override suspend fun load(loadParams: LoadParams<Int>): LoadResult<Int, PokemonEntity> {
                val page = loadParams.key ?: 0
                return try {
                    pokemonRepository.getPokemons(page * PAGE_SIZE, PAGE_SIZE).let {
                        LoadResult.Page(
                            data = it,
                            prevKey = null,
                            nextKey = if (it.size == PAGE_SIZE) page.plus(1) else null
                        )
                    }
                } catch (exception: Exception) {
                    LoadResult.Error(exception)
                }
            }
        }
    }.flow

    companion object {
        private const val PAGE_SIZE = 20
    }
}
