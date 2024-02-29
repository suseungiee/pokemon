package com.seungsu.pokemon.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class Colors(
    val DefaultGray: Color = Color(0xFF4B5563),
    val Gray: Color = Color(0xFFE5E7EB),
    val Red: Color = Color(0xFFC20847),
    val Black: Color = Color(0xFF000000),
    val White: Color = Color(0xFFFFFFFF),
    val Blue: Color = Color(0xFF0F2183),
    val Brown: Color = Color(0xFF997370),
    val Green: Color = Color(0xFF063A03),
    val Orange: Color = Color(0xFFE76339),
    val Yellow: Color = Color(0xFFFFEB3B),
    val DarkYellow: Color = Color(0xFFCCA633),
    val Purple: Color = Color(0xFF8B5BDF),
    val SkyBlue: Color = Color(0xFF3C93BB),
    val LightGreen: Color = Color(0xFFA7B332)
)

val LocalPokemonColors = staticCompositionLocalOf {
    Colors()
}
