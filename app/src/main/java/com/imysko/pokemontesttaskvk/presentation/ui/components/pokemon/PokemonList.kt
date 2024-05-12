package com.imysko.pokemontesttaskvk.presentation.ui.components.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonUiModel

@Composable
fun PokemonList(
    buffer: UInt = 3u,
    isLoading: Boolean = false,
    pokemonList: List<PokemonUiModel>,
    onCardClick: (Int) -> Unit,
    onLoadMore: () -> Unit,
) {
    val gridState = rememberLazyStaggeredGridState()

    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = gridState.layoutInfo.visibleItemsInfo.lastOrNull()

            lastVisibleItem?.let {
                it.index != 0 && it.index >= gridState.layoutInfo.totalItemsCount - buffer.toInt()
            } ?: run {
                true
            }
        }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) {
            onLoadMore()
        }
    }

    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = StaggeredGridCells.Fixed(2),
        state = gridState,
        contentPadding = PaddingValues(
            start = 20.dp,
            end = 20.dp,
            top = 20.dp,
            bottom = 80.dp,
        ),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalItemSpacing = 20.dp,
    ) {
        items(pokemonList) { pokemon ->
            PokemonCard(
                pokemon = pokemon,
                onCardClick = onCardClick,
            )
        }

        if (isLoading) {
            items(5) {
                PokemonCardPlaceholder()
            }
        }
    }
}
