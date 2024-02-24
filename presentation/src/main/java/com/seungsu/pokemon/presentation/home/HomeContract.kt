package com.seungsu.pokemon.presentation.home

import com.seungsu.pokemon.presentation.base.ViewEffect
import com.seungsu.pokemon.presentation.base.ViewIntent
import com.seungsu.pokemon.presentation.base.ViewState
import com.seungsu.pokemon.presentation.model.Pokemon

sealed interface HomeIntent: ViewIntent {

    @JvmInline
    value class OnClickPokemon(val id: Int): HomeIntent

}

data class HomeState(
    val message: String = "",
    val pokemons: List<Pokemon> = emptyList()
): ViewState

sealed interface HomeEffect: ViewEffect {
    @JvmInline
    value class NavigateToDetail(val id: Int): HomeEffect
}
