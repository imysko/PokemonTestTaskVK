package com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonList

import com.imysko.pokemontesttaskvk.domain.entities.Pokemon

sealed interface PokemonListUiState {

    data class ShowPokemonList(
        val pokemonList: List<Pokemon>,
    ) : PokemonListUiState

    data object OnLoading : PokemonListUiState

    data object NotFound : PokemonListUiState

    data object OnError : PokemonListUiState

    data object NoInternetConnection : PokemonListUiState
}