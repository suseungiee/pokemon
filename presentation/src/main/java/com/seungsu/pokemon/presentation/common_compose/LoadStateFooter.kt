package com.seungsu.pokemon.presentation.common_compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState

@Composable
fun LoadStateFooter(
    modifier: Modifier = Modifier,
    loadState: LoadState,
    onRetryClick: () -> Unit = {}
) {
    Box(modifier = modifier.fillMaxWidth()) {
        when (loadState) {
            is LoadState.Loading -> PagingLoadingScreen()
            is LoadState.Error -> PagingNetworkErrorScreen(
                onRetryClick = onRetryClick
            )
            else -> {}
        }
    }
}
