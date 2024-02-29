package com.seungsu.pokemon.presentation.common_compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seungsu.pokemon.presentation.theme.PokemonTheme

@Composable
fun PagingLoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun PagingLoadingScreenPreview() {
    PokemonTheme {
        PagingLoadingScreen()
    }
}
