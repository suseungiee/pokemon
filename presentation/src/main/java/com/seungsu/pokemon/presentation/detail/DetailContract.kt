package com.seungsu.pokemon.presentation.detail

import com.seungsu.pokemon.presentation.base.ViewEffect
import com.seungsu.pokemon.presentation.base.ViewIntent
import com.seungsu.pokemon.presentation.base.ViewState
import com.seungsu.pokemon.presentation.model.Pokemon

sealed interface DetailIntent : ViewIntent {
    data object OnClickRetry: DetailIntent
}

sealed interface DetailState : ViewState {
    data object Loading: DetailState
    data class Success(
        val pokemonInfo: Pokemon
    ): DetailState

    data object Error: DetailState
}

sealed interface DetailEffect : ViewEffect
