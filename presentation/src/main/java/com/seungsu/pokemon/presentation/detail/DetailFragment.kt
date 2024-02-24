package com.seungsu.pokemon.presentation.detail

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import coil.load
import com.seungsu.pokemon.presentation.base.MVIFragment
import com.seungsu.pokemon.presentation.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : MVIFragment<FragmentDetailBinding, DetailIntent, DetailState, DetailEffect>(
    FragmentDetailBinding::inflate
) {
    override val viewModel: DetailViewModel by viewModels()

    override fun initView() {
        with(binding) {
            layoutNetworkError.btnNetworkError.setOnClickListener { setIntent(DetailIntent.OnClickRetry) }
        }
    }

    override fun processState(state: DetailState) {
        with(binding) {
            pbLoading.isVisible = state is DetailState.Loading
            layoutNetworkError.root.isVisible = state is DetailState.Error
            groupContent.isVisible = state is DetailState.Success

            if (state is DetailState.Success) {
                ivPokemon.load(state.pokemonInfo.imageUrl)
                tvName.text = state.pokemonInfo.name
            }
        }
    }

    override fun processEffect(effect: DetailEffect) = Unit
}
