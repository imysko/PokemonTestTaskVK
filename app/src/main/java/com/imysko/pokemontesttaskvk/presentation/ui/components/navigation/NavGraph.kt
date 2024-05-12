package com.imysko.pokemontesttaskvk.presentation.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.imysko.pokemontesttaskvk.presentation.entities.navigation.NavArguments
import com.imysko.pokemontesttaskvk.presentation.entities.navigation.NavDestinations
import com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonDetail.PokemonDetailScreen
import com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonList.PokemonListScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = NavDestinations.POKEMON_LIST_SCREEN,
    ) {

        composable(route = NavDestinations.POKEMON_LIST_SCREEN) {
            PokemonListScreen(
                onNavigateToPokemonDetail = { id ->
                    navController.navigate(
                        route = "${NavDestinations.POKEMON_DETAIL_SCREEN}/${id}",
                    )
                }
            )
        }

        composable(
            route = "${NavDestinations.POKEMON_DETAIL_SCREEN}/{${NavArguments.POKEMON_ID}}",
            arguments = listOf(
                navArgument(NavArguments.POKEMON_ID) {
                    type = NavType.IntType
                }
            ),
        ) {
            PokemonDetailScreen(
                onBackNavigate = { navController.popBackStack() },
            )
        }
    }
}
