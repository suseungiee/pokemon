package com.seungsu.pokemon.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seungsu.pokemon.presentation.theme.PokemonTheme

@Composable
fun Stat(
    modifier: Modifier = Modifier,
    stat: String,
    value: String
) {
    Text(
        modifier = modifier
            .background(
                color = PokemonTheme.color.White,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(4.dp),
        text = buildAnnotatedString {
            append(stat)
            append(" ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(value)
            }
        },
        style = TextStyle(
            color = PokemonTheme.color.Black,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    )
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun StatPreview() {
    PokemonTheme {
        Stat(
            stat = "HP",
            value = "2475"
        )
    }
}
