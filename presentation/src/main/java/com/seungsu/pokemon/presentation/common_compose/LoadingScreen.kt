package com.seungsu.pokemon.presentation.common_compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seungsu.pokemon.presentation.theme.PokemonTheme

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp),
            color = PokemonTheme.color.Black
        )
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun LoadingScreenPreview() {
    PokemonTheme {
        LoadingScreen()
    }
}
