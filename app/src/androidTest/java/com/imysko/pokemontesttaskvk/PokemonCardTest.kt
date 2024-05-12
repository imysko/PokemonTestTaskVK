package com.imysko.pokemontesttaskvk

import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.imysko.pokemontesttaskvk.presentation.entities.AbilityUiModel
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonAbilityUiModel
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonTypeUiModel
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonUiModel
import com.imysko.pokemontesttaskvk.presentation.ui.components.pokemon.PokemonCard
import com.imysko.pokemontesttaskvk.presentation.ui.theme.PokemonTestTaskVKTheme
import com.imysko.pokemontesttaskvk.utils.TestTags
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            PokemonTestTaskVKTheme {
                PokemonCard(
                    pokemon = pokemon,
                    onCardClick = { },
                )
            }
        }
    }

    @Test
    fun pokemonTitleTest() {
        composeTestRule.onNodeWithTag(TestTags.POKEMON_CARD_TITLE, useUnmergedTree = true)
            .assertTextEquals(pokemon.name)
    }

    @Test
    fun pokemonTypesTest() {
        val count =
            composeTestRule.onAllNodesWithTag(TestTags.POKEMON_CARD_TYPE, useUnmergedTree = true)
                .fetchSemanticsNodes()
                .count()

        assert(count == pokemon.types.size)

        composeTestRule.onAllNodesWithTag(TestTags.POKEMON_CARD_TYPE, useUnmergedTree = true)
            .apply {
                pokemon.types.forEach {
                    assertAny(hasText(it.name))
                }
            }
    }

    companion object {

        private val pokemon = PokemonUiModel(
            id = 25,
            name = "Pikachu",
            height = 4,
            weight = 60,
            abilities = listOf(
                PokemonAbilityUiModel(
                    slotNumber = 1,
                    isHidden = false,
                    abilityName = "static",
                    ability = AbilityUiModel(
                        id = 9,
                        name = "Static",
                        description = "Has a 30% chance of paralyzing attacking Pokémon on contact.",
                    ),
                ),
                PokemonAbilityUiModel(
                    slotNumber = 3,
                    isHidden = true,
                    abilityName = "lightning-rod",
                    ability = AbilityUiModel(
                        id = 31,
                        name = "Lightning rod",
                        description = "Redirects single-target electric moves to this Pokémon where possible.  Absorbs Electric moves, raising Special Attack one stage.",
                    ),
                ),
            ),
            types = listOf(
                PokemonTypeUiModel(
                    slotNumber = 1,
                    name = "Electric",
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
