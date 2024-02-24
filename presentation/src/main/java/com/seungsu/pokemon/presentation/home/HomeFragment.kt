package com.seungsu.pokemon.presentation.home

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.seungsu.pokemon.presentation.base.MVIFragment
import com.seungsu.pokemon.presentation.databinding.FragmentHomeBinding
import com.seungsu.pokemon.presentation.ext.safeNavigate
import com.seungsu.pokemon.presentation.home.adapter.GridSpacingItemDecoration
import com.seungsu.pokemon.presentation.home.adapter.LoadAdapter
import com.seungsu.pokemon.presentation.home.adapter.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: MVIFragment<
        FragmentHomeBinding,
        HomeIntent,
        HomeState,
        HomeEffect
        >(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel by viewModels()

    private val adapter: PokemonAdapter by lazy {
        PokemonAdapter { setIntent(HomeIntent.OnClickPokemon(it)) }
    }
    override fun initView() {
        with(binding) {
            rvPokemon.apply {
                adapter = this@HomeFragment.adapter.withLoadStateFooter(
                    LoadAdapter { this@HomeFragment.adapter.retry() }
                )
                addItemDecoration(GridSpacingItemDecoration())
                layoutManager = GridLayoutManager(requireContext(), 3)
                setHasFixedSize(true)
            }
            layoutNetworkError.btnNetworkError.setOnClickListener { adapter.retry() }
        }
        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.homeResult.collectLatest {
                        adapter.submitData(it)
                    }
                }

                launch {
                    adapter.loadStateFlow
                        .distinctUntilChangedBy { it.refresh }
                        .collect { loadState ->
                            val state = loadState.source.refresh
                            with(binding) {
                                pbLoading.isVisible = state is LoadState.Loading && adapter.itemCount == 0
                                rvPokemon.isVisible = state is LoadState.NotLoading && adapter.itemCount > 0
                                layoutNetworkError.root.isVisible = state is LoadState.Error
                            }
                        }
                }
            }
        }
    }

    override fun processState(state: HomeState) = Unit

    override fun processEffect(effect: HomeEffect) = when(effect) {
        is HomeEffect.NavigateToDetail ->
            findNavController().safeNavigate(HomeFragmentDirections.actionHomeFragmentToDetail(effect.id))
    }
}
