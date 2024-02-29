package com.seungsu.pokemon.presentation.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.seungsu.pokemon.presentation.common_compose.LoadStateFooter
import com.seungsu.pokemon.presentation.common_compose.LoadingScreen
import com.seungsu.pokemon.presentation.common_compose.NetworkErrorScreen
import com.seungsu.pokemon.presentation.theme.PokemonTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToDetail: (String) -> Unit
) {
    val pokemons = viewModel.pokemons.collectAsLazyPagingItems()

    LaunchedEffect(true) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeEffect.NavigateToDetail -> {
                    onNavigateToDetail(effect.name)
                }
            }
        }
    }

    when (pokemons.loadState.refresh) {
        is LoadState.Loading -> LoadingScreen()
        is LoadState.Error -> NetworkErrorScreen { pokemons.retry() }
        is LoadState.NotLoading -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                items(
                    count = pokemons.itemCount,
                    key = pokemons.itemKey(),
                    contentType = pokemons.itemContentType()
                ) { index ->
                    pokemons[index]?.let {
                        Column(
                            modifier = Modifier
                                .clickable { viewModel.dispatch(HomeIntent.OnClickPokemon(it.name)) }
                                .border(
                                    width = 11.dp,
                                    color = PokemonTheme.color.DefaultGray,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .wrapContentSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(it.imageUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "image",
                                modifier = Modifier
                                    .padding(top = 11.dp)
                                    .size(100.dp)
                                    .aspectRatio(1f),
                                contentScale = ContentScale.FillBounds
                            )
                            Text(
                                text = it.name,
                                style = TextStyle(
                                    color = PokemonTheme.color.Black,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center
                                ),
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                        }
                    }
                }

                item {
                    LoadStateFooter(
                        loadState = pokemons.loadState.append,
                        onRetryClick = { pokemons.retry() }
                    )
                }
            }
        }
    }
}
