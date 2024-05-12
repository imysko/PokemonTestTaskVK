package com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonList

import com.imysko.pokemontesttaskvk.presentation.entities.PokemonUiModel

sealed interface PokemonListScreenUiState {

    data class ShowPokemonList(
        val pokemonList: List<PokemonUiModel> = emptyList(),
        val isLoadingMore: Boolean = false,
    ) : PokemonListScreenUiState

    data object OnLoading : PokemonListScreenUiState

    data object NotFound : PokemonListScreenUiState

    data object OnError : PokemonListScreenUiState

    data object NoInternetConnection : PokemonListScreenUiState
}
