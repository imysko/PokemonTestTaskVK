package com.imysko.pokemontesttaskvk

import com.imysko.pokemontesttaskvk.data.MockPokemonRepository
import com.imysko.pokemontesttaskvk.domain.entities.Pokemon
import com.imysko.pokemontesttaskvk.domain.entities.PokemonAbility
import com.imysko.pokemontesttaskvk.domain.entities.PokemonType
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonListUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.Test

class GetPokemonListUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `WHEN pokemon repository return pokemon list`() = runTest {
        val useCase = GetPokemonListUseCaseImpl(pokemonRepository)

        val actualResult = useCase().flatMapConcat { it.asFlow() }.toList()

        val expectedResult = stubPokemonList()

        Assertions.assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult)
    }

    companion object {

        private val pokemonRepository = MockPokemonRepository()

        private fun stubPokemonList(): List<Pokemon> = listOf(
            Pokemon(
                id = 1,
                name = "bulbasaur",
                height = 4,
                weight = 60,
                abilities = listOf(
                    PokemonAbility(
                        slot = 1,
                        isHidden = false,
                        abilityName = "overgrow",
                    ),
                    PokemonAbility(
                        slot = 3,
                        isHidden = true,
                        abilityName = "chlorophyll",
                    ),
                ),
                types = listOf(
                    PokemonType(
                        slotNumber = 1,
                        name = "grass",
                    ),
                    PokemonType(
                        slotNumber = 2,
                        name = "poison",
                    ),
                ),
                crySoundUrl = "https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/latest/1.ogg",
                logoUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg",
                imagesUrl = listOf(
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/1.png",
                ),
            ),
            Pokemon(
                id = 25,
                name = "pikachu",
                height = 4,
                weight = 60,
                abilities = listOf(
                    PokemonAbility(
                        slot = 1,
                        isHidden = false,
                        abilityName = "static",
                    ),
                    PokemonAbility(
                        slot = 3,
                        isHidden = true,
                        abilityName = "lightning-rod",
                    ),
                ),
                types = listOf(
                    PokemonType(
                        slotNumber = 1,
                        name = "electric",
                    ),
                ),
                crySoundUrl = "https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/latest/25.ogg",
                logoUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/25.svg",
                imagesUrl = listOf(
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/25.svg",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/25.png",
                ),
            )
        )
    }
}
