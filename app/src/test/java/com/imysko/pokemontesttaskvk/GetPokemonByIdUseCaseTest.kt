package com.imysko.pokemontesttaskvk

import com.imysko.pokemontesttaskvk.data.MockPokemonRepository
import com.imysko.pokemontesttaskvk.domain.entities.Pokemon
import com.imysko.pokemontesttaskvk.domain.entities.PokemonAbility
import com.imysko.pokemontesttaskvk.domain.entities.PokemonType
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonByIdUseCaseImpl
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetPokemonByIdUseCaseTest {

    @Test
    fun `WHEN pokemon repository return not null value`() = runTest {
        val useCase = GetPokemonByIdUseCaseImpl(pokemonRepository)

        val actualResult = useCase(25)
        val expectedResult = stubPokemon()

        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN pokemon repository return null value`() {
        val useCase = GetPokemonByIdUseCaseImpl(pokemonRepository)

        val actualResult = useCase(22)
        val expectedResult = null

        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult)
    }

    companion object {

        private val pokemonRepository = MockPokemonRepository()

        private fun stubPokemon(): Pokemon = Pokemon(
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
    }
}
