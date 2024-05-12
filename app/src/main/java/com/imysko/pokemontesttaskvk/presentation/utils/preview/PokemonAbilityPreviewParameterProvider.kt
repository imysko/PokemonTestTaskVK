package com.imysko.pokemontesttaskvk.presentation.utils.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.imysko.pokemontesttaskvk.presentation.entities.AbilityUiModel
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonAbilityUiModel

class PokemonAbilityPreviewParameterProvider : PreviewParameterProvider<PokemonAbilityUiModel> {

    override val values = sequenceOf(
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
