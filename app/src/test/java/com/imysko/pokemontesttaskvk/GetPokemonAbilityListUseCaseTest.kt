package com.imysko.pokemontesttaskvk

import com.imysko.pokemontesttaskvk.data.MockPokemonRepository
import com.imysko.pokemontesttaskvk.domain.entities.PokemonAbility
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonAbilityListUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetPokemonAbilityListUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `WHEN pokemon repository return list of abilities`() = runTest {
        val useCase = GetPokemonAbilityListUseCaseImpl(pokemonRepository)

        val actualResult = useCase(1).flatMapConcat { it.asFlow() }.toList()
        val expectedResult = stubPokemonAbilities()

        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `WHEN pokemon repository return empty list`() = runTest {
        val useCase = GetPokemonAbilityListUseCaseImpl(pokemonRepository)

        val actualResult = useCase(6).flatMapConcat { it.asFlow() }.toList()
        val expectedResult = emptyList<PokemonAbility>()

        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult)
    }

    companion object {

        private val pokemonRepository = MockPokemonRepository()

        private fun stubPokemonAbilities(): List<PokemonAbility> = listOf(
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
        )
    }
}
