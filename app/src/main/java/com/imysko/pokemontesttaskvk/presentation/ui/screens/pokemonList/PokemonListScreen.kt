package com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imysko.pokemontesttaskvk.presentation.ui.components.PokemonCard

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel(),
    onNavigateToPokemonDetail: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    PokemonListScreen(
        uiState = uiState,
        onCardClick = onNavigateToPokemonDetail,
    )
}

@Composable
fun PokemonListScreen(
    uiState: PokemonListUiState,
    onCardClick: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            Text(text = "список")
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                when (uiState) {
                    is PokemonListUiState.ShowPokemonList -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(
                                vertical = 20.dp,
                                horizontal = 15.dp,
                            ),
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                        ) {
                            items(uiState.pokemonList) { pokemon ->
                                PokemonCard(
                                    pokemon = pokemon,
                                    onCardClick = onCardClick,
                                )
                            }
                        }
                    }

                    PokemonListUiState.OnLoading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    }

                    else -> Unit
                }
            }
        }
    )
}
