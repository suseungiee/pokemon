package com.seungsu.pokemon.presentation.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.seungsu.pokemon.domain.model.PokemonEntity
import com.seungsu.pokemon.domain.usecase.GetPokemonsUseCase
import com.seungsu.pokemon.presentation.base.MVIViewModel
import com.seungsu.pokemon.presentation.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest

@HiltViewModel
class HomeViewModel @Inject constructor(
    getPokemonsUseCase: GetPokemonsUseCase
) : MVIViewModel<HomeIntent, HomeState, HomeEffect>() {

    val homeResult = loadDataSignal
        .flatMapLatest { getPokemonsUseCase(Unit).mapLatest { pagingData -> pagingData.map(PokemonEntity::toUiModel) } }
        .cachedIn(viewModelScope)

    override fun createInitialState() = HomeState()

    override fun proecessIntent(intent: HomeIntent) = when(intent) {
        is HomeIntent.OnClickPokemon -> navigateToDetail(intent.name)
    }

    private fun navigateToDetail(name: String) = currentState {
        setEffect(HomeEffect.NavigateToDetail(name))
    }
}
