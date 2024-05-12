package com.imysko.pokemontesttaskvk

import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.imysko.pokemontesttaskvk.presentation.entities.AbilityUiModel
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonAbilityUiModel
import com.imysko.pokemontesttaskvk.presentation.ui.components.pokemon.PokemonAbilityList
import com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonDetail.AbilityListUiState
import com.imysko.pokemontesttaskvk.presentation.ui.theme.PokemonTestTaskVKTheme
import com.imysko.pokemontesttaskvk.utils.TestTags
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonAbilityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            PokemonTestTaskVKTheme {
                PokemonAbilityList(
                    abilityListUiState = AbilityListUiState.ShowAbilityList(abilityList),
                    count = abilityList.size,
                    onEvent = { },
                )
            }
        }
    }

    @Test
    fun pokemonAbilityNameTest() {
        val count =
            composeTestRule.onAllNodesWithTag(TestTags.POKEMON_ABILITY_NAME, useUnmergedTree = true)
                .fetchSemanticsNodes()
                .count()

        assert(count == abilityList.filter { it.ability != null }.size)

        composeTestRule.onAllNodesWithTag(TestTags.POKEMON_ABILITY_NAME).apply {
            abilityList.forEach {
                it.ability?.let { ability ->
                    assertAny(hasText(ability.name))
                }
            }
        }
    }

    @Test
    fun pokemonAbilityIsHiddenTest() {
        composeTestRule.onAllNodesWithTag(TestTags.POKEMON_ABILITY_ITEM).apply {
            abilityList.filter { it.ability != null }
                .forEach { pokemonAbilityUiModel ->
                    if (pokemonAbilityUiModel.isHidden) {
                        hasAnyChild(hasTestTag(TestTags.POKEMON_ABILITY_IS_HIDDEN))
                    }
                }
        }
    }

    companion object {

        private val abilityList = listOf(
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
            PokemonAbilityUiModel(
                slotNumber = 3,
                isHidden = true,
                abilityName = "lightning-rod",
                ability = null,
            ),
        )
    }
}