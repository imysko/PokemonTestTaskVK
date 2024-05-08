package com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonDetail

import com.imysko.pokemontesttaskvk.domain.entities.Pokemon

sealed interface PokemonDetailUiState {

    data class ShowPokemonDetail(
        val pokemon: Pokemon,
    ) : PokemonDetailUiState

    data object OnLoading : PokemonDetailUiState

    data object OnError : PokemonDetailUiState

    data object NoInternetConnection : PokemonDetailUiState
}
