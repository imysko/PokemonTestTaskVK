package com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonDetail

sealed interface PokemonDetailScreenEvent {

    data object OnPlayButtonClick : PokemonDetailScreenEvent
    data object OnPauseButtonClick : PokemonDetailScreenEvent
    data object OnReloadAbilityList : PokemonDetailScreenEvent
}
