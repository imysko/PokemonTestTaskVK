package com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.imysko.pokemontesttaskvk.R
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonUiModel
import com.imysko.pokemontesttaskvk.presentation.ui.components.base.ErrorPlaceholder
import com.imysko.pokemontesttaskvk.presentation.ui.components.base.NoInternetConnectionPlaceholder
import com.imysko.pokemontesttaskvk.presentation.ui.components.base.TopBarWithReturn
import com.imysko.pokemontesttaskvk.presentation.ui.components.pokemon.PokemonList
import com.imysko.pokemontesttaskvk.presentation.ui.theme.PokemonTestTaskVKTheme
import com.imysko.pokemontesttaskvk.presentation.utils.preview.PokemonListPreviewParameterProvider

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel(),
    onNavigateToPokemonDetail: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    PokemonListScreen(
        uiState = uiState,
        onCardClick = onNavigateToPokemonDetail,
        onLoadMore = viewModel::loadPokemonNamesList,
        onReloadButtonClick = viewModel::reloadPokemonNamesList,
    )
}

@Composable
fun PokemonListScreen(
    uiState: PokemonListScreenUiState,
    onCardClick: (Int) -> Unit,
    onLoadMore: () -> Unit,
    onReloadButtonClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarWithReturn(title = stringResource(id = R.string.pokemon_list))
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                when (uiState) {
                    is PokemonListScreenUiState.ShowPokemonList -> {
                        PokemonList(
                            isLoading = uiState.isLoadingMore,
                            pokemonList = uiState.pokemonList,
                            onCardClick = onCardClick,
                            onLoadMore = onLoadMore,
                        )
                    }

                    PokemonListScreenUiState.OnLoading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    }

                    PokemonListScreenUiState.NotFound -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = stringResource(id = R.string.not_found),
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                ),
                            )
                        }
                    }

                    PokemonListScreenUiState.OnError -> {
                        ErrorPlaceholder(
                            onReloadButtonClick = onReloadButtonClick,
                        )
                    }

                    PokemonListScreenUiState.NoInternetConnection -> {
                        NoInternetConnectionPlaceholder(
                            onReloadButtonClick = onReloadButtonClick,
                        )
                    }
                }
            }
        },
    )
}

@Composable
@Preview(group = "Pokemon list", name = "Show list")
private fun PokemonListScreenPreview(
    @PreviewParameter(PokemonListPreviewParameterProvider::class) parameter: List<PokemonUiModel>,
) {
    PokemonTestTaskVKTheme {
        PokemonListScreen(
            uiState = PokemonListScreenUiState.ShowPokemonList(parameter),
            onCardClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
        )
    }
}

@Composable
@Preview(group = "Pokemon list", name = "Loading")
private fun PokemonListLoadingScreenPreview() {
    PokemonTestTaskVKTheme {
        PokemonListScreen(
            uiState = PokemonListScreenUiState.OnLoading,
            onCardClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
        )
    }
}

@Composable
@Preview(group = "Pokemon list", name = "Loading more")
private fun PokemonListLoadingMoreScreenPreview(
    @PreviewParameter(PokemonListPreviewParameterProvider::class) parameter: List<PokemonUiModel>,
) {
    PokemonTestTaskVKTheme {
        PokemonListScreen(
            uiState = PokemonListScreenUiState.ShowPokemonList(
                pokemonList = parameter,
                isLoadingMore = true,
            ),
            onCardClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
        )
    }
}

@Composable
@Preview(group = "Pokemon list", name = "Not found")
private fun PokemonListNotFoundScreenPreview() {
    PokemonTestTaskVKTheme {
        PokemonListScreen(
            uiState = PokemonListScreenUiState.NotFound,
            onCardClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
        )
    }
}

@Composable
@Preview(group = "Pokemon list", name = "Error")
private fun PokemonListErrorScreenPreview() {
    PokemonTestTaskVKTheme {
        PokemonListScreen(
            uiState = PokemonListScreenUiState.OnError,
            onCardClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
        )
    }
}

@Composable
@Preview(group = "Pokemon list", name = "No internet connection")
private fun PokemonListNoInternetConnectionScreenPreview() {
    PokemonTestTaskVKTheme {
        PokemonListScreen(
            uiState = PokemonListScreenUiState.NoInternetConnection,
            onCardClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
        )
    }
}
