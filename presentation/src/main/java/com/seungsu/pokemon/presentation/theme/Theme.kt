package com.seungsu.pokemon.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

@Composable
fun PokemonTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDensity provides Density(density = LocalDensity.current.density, fontScale = 1f),
        LocalPokemonColors provides Colors()
    ) {
        MaterialTheme {
            content()
        }
    }
}

object PokemonTheme {
    val color: Colors
        @Composable
        get() = LocalPokemonColors.current
}
