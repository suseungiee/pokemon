package com.seungsu.pokemon.presentation.common_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seungsu.pokemon.presentation.R
import com.seungsu.pokemon.presentation.theme.PokemonTheme

@Composable
fun PagingNetworkErrorScreen(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.network_error),
            style = TextStyle(
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = PokemonTheme.color.Black
            )
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .size(24.dp))
        Button(
            onClick = { onRetryClick() },
            modifier = Modifier
                .background(
                    color = PokemonTheme.color.DefaultGray,
                    shape = RoundedCornerShape(20.dp)
                )
                .width(200.dp)
        ) {
            Text(
                text = stringResource(id = R.string.retry),
                style = TextStyle(
                    color = PokemonTheme.color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun PagingNetworkErrorScreenPreview() {
    PokemonTheme {
        PagingNetworkErrorScreen()
    }
}
