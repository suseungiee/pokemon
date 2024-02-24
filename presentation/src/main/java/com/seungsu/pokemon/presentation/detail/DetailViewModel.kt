package com.seungsu.pokemon.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.seungsu.pokemon.domain.base.ApiResult
import com.seungsu.pokemon.domain.base.asResult
import com.seungsu.pokemon.domain.usecase.GetPokemonInfoByIdUseCase
import com.seungsu.pokemon.presentation.base.MVIViewModel
import com.seungsu.pokemon.presentation.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPokemonInfoByIdUseCase: GetPokemonInfoByIdUseCase,
    private val savedStateHandle: SavedStateHandle,
) : MVIViewModel<DetailIntent, DetailState, DetailEffect>() {
    private val pokemonId
        get() = savedStateHandle.get<Int>(KEY_POKEMON_ID) ?: -1

    private val detailResult = loadDataSignal
        .flatMapLatest { getPokemonInfoByIdUseCase(pokemonId).asResult() }
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading)

    init {
        viewModelScope.launch {
            detailResult.collect { apiResult ->
                setState {
                    when (apiResult) {
                        is ApiResult.Loading -> DetailState.Loading
                        is ApiResult.Success -> DetailState.Success(pokemonInfo = apiResult.data.toUiModel())
                        is ApiResult.Error -> DetailState.Error
                    }
                }
            }
        }
    }
    override fun createInitialState() = DetailState.Loading

    override fun proecessIntent(intent: DetailIntent) = when (intent) {
        DetailIntent.OnClickRetry -> onRefresh()
    }

    companion object {
        private const val KEY_POKEMON_ID = "pokemon_id"
    }
}
