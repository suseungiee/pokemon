package com.seungsu.pokemon.presentation.home

import com.seungsu.pokemon.presentation.base.ViewEffect
import com.seungsu.pokemon.presentation.base.ViewIntent
import com.seungsu.pokemon.presentation.base.ViewState
import com.seungsu.pokemon.presentation.model.SimplePokemon

sealed interface HomeIntent: ViewIntent {

    @JvmInline
    value class OnClickPokemon(val name: String): HomeIntent

}

data class HomeState(
    val message: String = "",
    val pokemons: List<SimplePokemon> = emptyList()
): ViewState

sealed interface HomeEffect: ViewEffect {
    @JvmInline
    value class NavigateToDetail(val name: String): HomeEffect
}
