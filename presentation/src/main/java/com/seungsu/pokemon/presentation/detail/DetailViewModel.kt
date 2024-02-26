package com.seungsu.pokemon.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.seungsu.pokemon.domain.base.ApiResult
import com.seungsu.pokemon.domain.base.asResult
import com.seungsu.pokemon.domain.usecase.GetPokemonInfoByNameUseCase
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
    private val getPokemonInfoByNameUseCase: GetPokemonInfoByNameUseCase,
    private val savedStateHandle: SavedStateHandle,
) : MVIViewModel<DetailIntent, DetailState, DetailEffect>() {
    private val pokemonName
        get() = savedStateHandle.get<String>(KEY_POKEMON_NAME) ?: ""

    private val detailResult = loadDataSignal
        .flatMapLatest { getPokemonInfoByNameUseCase(pokemonName).asResult() }
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
        private const val KEY_POKEMON_NAME = "pokemon_name"
    }
}
