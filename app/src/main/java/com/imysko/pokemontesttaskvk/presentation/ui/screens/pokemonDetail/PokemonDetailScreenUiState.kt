package com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonDetail

import com.imysko.pokemontesttaskvk.presentation.entities.PokemonAbilityUiModel
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonUiModel

sealed interface PokemonDetailUiState {

    data class ShowPokemonDetail(
        val pokemon: PokemonUiModel,
    ) : PokemonDetailUiState
    data object OnLoading : PokemonDetailUiState
    data object OnError : PokemonDetailUiState
}

sealed interface AbilityListUiState {

    data class ShowAbilityList(
        val abilityList: List<PokemonAbilityUiModel> = emptyList(),
    ) : AbilityListUiState

    data object OnLoading : AbilityListUiState
    data object OnError : AbilityListUiState
    data object NoInternetConnection : AbilityListUiState
}
