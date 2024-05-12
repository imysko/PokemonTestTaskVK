package com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonUiModel
import com.imysko.pokemontesttaskvk.presentation.ui.components.base.ErrorPlaceholder
import com.imysko.pokemontesttaskvk.presentation.ui.components.base.TopBarWithReturn
import com.imysko.pokemontesttaskvk.presentation.ui.components.pokemon.PokemonDetailBody
import com.imysko.pokemontesttaskvk.presentation.ui.theme.PokemonTestTaskVKTheme
import com.imysko.pokemontesttaskvk.presentation.utils.preview.PokemonPreviewParameterProvider

@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel = hiltViewModel(),
    onBackNavigate: () -> Unit,
) {
    val pokemonDetailUiState by viewModel.pokemonDetailUiState.collectAsState()
    val abilityListUiState by viewModel.abilityListUiState.collectAsState()

    val isPlayingSound = viewModel.isPlayingSound

    PokemonDetailScreen(
        pokemonDetailUiState = pokemonDetailUiState,
        abilityListUiState = abilityListUiState,
        isPlayingSound = isPlayingSound,
        onEvent = viewModel::onEvent,
        onBackNavigate = onBackNavigate,
    )
}

@Composable
fun PokemonDetailScreen(
    pokemonDetailUiState: PokemonDetailUiState,
    abilityListUiState: AbilityListUiState?,
    isPlayingSound: Boolean,
    onEvent: (PokemonDetailScreenEvent) -> Unit,
    onBackNavigate: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarWithReturn(
                onBackClick = onBackNavigate,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                when (pokemonDetailUiState) {
                    is PokemonDetailUiState.ShowPokemonDetail -> {
                        PokemonDetailBody(
                            pokemon = pokemonDetailUiState.pokemon,
                            abilityListUiState = abilityListUiState,
                            isPlayingSound = isPlayingSound,
                            onEvent = onEvent,
                        )
                    }

                    is PokemonDetailUiState.OnLoading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    }

                    is PokemonDetailUiState.OnError -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                        ) {
                            ErrorPlaceholder(
                                modifier = Modifier.align(Alignment.Center),
                                onReloadButtonClick = {

                                }
                            )
                        }
                    }
                }
            }
        },
    )
}

@Composable
@Preview(group = "Pokemon detail screen", name = "Show pokemon detail")
private fun PokemonDetailScreenPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) parameter: PokemonUiModel,
) {
    PokemonTestTaskVKTheme {
        PokemonTestTaskVKTheme {
            PokemonDetailScreen(
                pokemonDetailUiState = PokemonDetailUiState.ShowPokemonDetail(parameter),
                abilityListUiState = null,
                isPlayingSound = false,
                onEvent = { },
                onBackNavigate = { },
            )
        }
    }
}

@Composable
@Preview(group = "Pokemon detail screen", name = "Is loading")
private fun PokemonDetailOnLoadingScreenPreview() {
    PokemonTestTaskVKTheme {
        PokemonTestTaskVKTheme {
            PokemonDetailScreen(
                pokemonDetailUiState = PokemonDetailUiState.OnLoading,
                abilityListUiState = null,
                isPlayingSound = false,
                onEvent = { },
                onBackNavigate = { },
            )
        }
    }
}

@Composable
@Preview(group = "Pokemon detail screen", name = "Error")
private fun PokemonDetailErrorScreenPreview() {
    PokemonTestTaskVKTheme {
        PokemonTestTaskVKTheme {
            PokemonDetailScreen(
                pokemonDetailUiState = PokemonDetailUiState.OnError,
                abilityListUiState = null,
                isPlayingSound = false,
                onEvent = { },
                onBackNavigate = { },
            )
        }
    }
}

@Composable
@Preview(group = "Ability list", name = "Show abilities")
private fun PokemonDetailScreenAbilitiesPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) parameter: PokemonUiModel,
) {
    PokemonTestTaskVKTheme {
        PokemonTestTaskVKTheme {
            PokemonDetailScreen(
                pokemonDetailUiState = PokemonDetailUiState.ShowPokemonDetail(parameter),
                abilityListUiState = AbilityListUiState.ShowAbilityList(parameter.abilities),
                isPlayingSound = false,
                onEvent = { },
                onBackNavigate = { },
            )
        }
    }
}

@Composable
@Preview(group = "Ability list", name = "Is loading")
private fun PokemonDetailScreenAbilitiesLoadingPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) parameter: PokemonUiModel,
) {
    PokemonTestTaskVKTheme {
        PokemonTestTaskVKTheme {
            PokemonDetailScreen(
                pokemonDetailUiState = PokemonDetailUiState.ShowPokemonDetail(parameter),
                abilityListUiState = AbilityListUiState.OnLoading,
                isPlayingSound = false,
                onEvent = { },
                onBackNavigate = { },
            )
        }
    }
}

@Composable
@Preview(group = "Ability list", name = "No internet connection")
private fun PokemonDetailScreenAbilitiesNoInternetConnectionPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) parameter: PokemonUiModel,
) {
    PokemonTestTaskVKTheme {
        PokemonTestTaskVKTheme {
            PokemonDetailScreen(
                pokemonDetailUiState = PokemonDetailUiState.ShowPokemonDetail(parameter),
                abilityListUiState = AbilityListUiState.NoInternetConnection,
                isPlayingSound = false,
                onEvent = { },
                onBackNavigate = { },
            )
        }
    }
}

@Composable
@Preview(group = "Ability list", name = "Error abilities")
private fun PokemonDetailScreenAbilitiesErrorPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) parameter: PokemonUiModel,
) {
    PokemonTestTaskVKTheme {
        PokemonTestTaskVKTheme {
            PokemonDetailScreen(
                pokemonDetailUiState = PokemonDetailUiState.ShowPokemonDetail(parameter),
                abilityListUiState = AbilityListUiState.OnError,
                isPlayingSound = false,
                onEvent = { },
                onBackNavigate = { },
            )
        }
    }
}
