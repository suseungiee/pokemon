package com.seungsu.pokemon.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.seungsu.pokemon.presentation.R
import com.seungsu.pokemon.presentation.common_compose.LoadingScreen
import com.seungsu.pokemon.presentation.common_compose.NetworkErrorScreen
import com.seungsu.pokemon.presentation.model.Pokemon
import com.seungsu.pokemon.presentation.model.Type
import com.seungsu.pokemon.presentation.theme.PokemonTheme
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onPopback: () -> Unit
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { onPopback() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow),
                            tint = PokemonTheme.color.DefaultGray,
                            contentDescription = "back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PokemonTheme.color.White
                )
            )
        },
        modifier = Modifier.border(
            11.dp,
            color = PokemonTheme.color.DefaultGray,
            shape = RoundedCornerShape(20.dp)
        )
    ) { paddingValues ->
        when (uiState) {
            is DetailState.Loading -> LoadingScreen()
            is DetailState.Success -> DetailLoaded(
                modifier = Modifier.padding(paddingValues),
                uiState.pokemonInfo
            )

            is DetailState.Error -> NetworkErrorScreen {
                viewModel.dispatch(DetailIntent.OnClickRetry)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailLoaded(
    modifier: Modifier = Modifier,
    pokemonInfo: Pokemon
) {
    Column(
        modifier = modifier.background(color = PokemonTheme.color.White)
    ) {
        Box(
            Modifier
                .wrapContentHeight()
                .weight(5f)
        ) {
            AsyncImage(
                model = pokemonInfo.imageUrl,
                contentDescription = "image",
                modifier = Modifier
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .fillMaxSize()
                    .aspectRatio(1f)
            )
            Box(
                modifier = Modifier
                    .padding(24.dp)
                    .background(
                        color = PokemonTheme.color.DefaultGray,
                        shape = CircleShape
                    )
                    .padding(24.dp)
                    .align(Alignment.TopEnd)
            )
            Text(
                text = String.format("%03d", pokemonInfo.id),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = PokemonTheme.color.White,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(36.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
                .background(PokemonTheme.color.Gray)
                .padding(24.dp)
        ) {
            Text(
                text = pokemonInfo.name,
                modifier = Modifier.padding(end = 8.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PokemonTheme.color.Black,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(16.dp)
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                pokemonInfo.types.map {
                    Text(
                        text = it,
                        modifier = Modifier
                            .background(
                                color = getColorByType(it),
                                shape = RoundedCornerShape(14.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .width(50.dp),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = PokemonTheme.color.White,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(16.dp)
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Stat(
                    stat = "W",
                    value = "${DecimalFormat("#.0").format(pokemonInfo.weight / 10)} kg"
                )
                Stat(
                    stat = "H",
                    value = "${DecimalFormat("#.0").format(pokemonInfo.height / 10)} M"
                )
            }
        }
    }
}

@Composable
fun getColorByType(type: String): Color {
    return when (type.toUpperCase()) {
        Type.GRASS.toString() -> PokemonTheme.color.Green
        Type.WATER.toString() -> PokemonTheme.color.Blue
        Type.FIRE.toString() -> PokemonTheme.color.Red
        Type.ELECTRIC.toString() -> PokemonTheme.color.Yellow
        Type.POISON.toString() -> PokemonTheme.color.DarkYellow
        Type.GROUND.toString() -> PokemonTheme.color.Brown
        Type.PSYCHIC.toString() -> PokemonTheme.color.Purple
        Type.ICE.toString() -> PokemonTheme.color.SkyBlue
        Type.DRAGON.toString() -> PokemonTheme.color.Orange
        Type.BUG.toString() -> PokemonTheme.color.LightGreen
        else -> PokemonTheme.color.DefaultGray
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun DetailLoadedPreview() {
    PokemonTheme {
        DetailLoaded(
            pokemonInfo = Pokemon(
                id = 243,
                imageUrl = "",
                name = "Nidoking"
            )
        )
    }

}
