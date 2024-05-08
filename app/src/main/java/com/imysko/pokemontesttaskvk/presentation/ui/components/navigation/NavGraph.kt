package com.imysko.pokemontesttaskvk.presentation.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.imysko.pokemontesttaskvk.domain.entities.navigation.NavDestinations
import com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonDetail.PokemonDetailScreen
import com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonList.PokemonListScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {

    NavHost(navController, startDestination = NavDestinations.PokemonList) {
        composable<NavDestinations.PokemonList> {
            PokemonListScreen(
                onNavigateToPokemonDetail = { id ->
                    navController.navigate(NavDestinations.PokemonDetail(id))
                },
            )
        }
        composable<NavDestinations.PokemonDetail> { backStackEntry ->
            val pokemonDetail: NavDestinations.PokemonDetail = backStackEntry.toRoute()
            PokemonDetailScreen(pokemonDetail)
        }
    }
}
