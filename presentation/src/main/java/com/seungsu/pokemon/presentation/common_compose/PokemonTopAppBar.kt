package com.seungsu.pokemon.presentation.common_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seungsu.pokemon.presentation.theme.PokemonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTopAppBar(
    modifier: Modifier = Modifier,
    thickness: Dp = 0.5.dp,
    titleString: String = "",
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    Column(modifier = modifier) {
        TopAppBar(
            modifier = modifier,
            title = {
                Text(
                    text = titleString,
                    fontSize = 18.sp,
                    color = PokemonTheme.color.Black
                )
            },
            navigationIcon = navigationIcon,
            actions = actions,
            windowInsets = WindowInsets(top = 0.dp)
        )
        if (thickness != 0.dp) {
            Divider(color = PokemonTheme.color.Black, thickness = thickness)
        }
    }
}
