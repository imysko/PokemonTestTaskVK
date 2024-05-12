package com.imysko.pokemontesttaskvk.presentation.utils.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.imysko.pokemontesttaskvk.presentation.entities.AbilityUiModel
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonAbilityUiModel
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonTypeUiModel
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonUiModel

class PokemonPreviewParameterProvider : PreviewParameterProvider<PokemonUiModel> {

    override val values = sequenceOf(
        PokemonUiModel(
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
        ),
    )
}
