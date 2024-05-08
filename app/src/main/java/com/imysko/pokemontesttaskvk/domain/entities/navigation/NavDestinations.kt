package com.imysko.pokemontesttaskvk.domain.entities.navigation

import kotlinx.serialization.Serializable

sealed interface NavDestinations {

    @Serializable
    data object PokemonList : NavDestinations

    @Serializable
    data class PokemonDetail(val id: Int) : NavDestinations
}
