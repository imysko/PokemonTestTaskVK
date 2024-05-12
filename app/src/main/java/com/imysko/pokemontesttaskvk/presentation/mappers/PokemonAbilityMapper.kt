package com.imysko.pokemontesttaskvk.presentation.mappers

import com.imysko.pokemontesttaskvk.domain.entities.PokemonAbility
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonAbilityUiModel

fun PokemonAbility.mapToUiModel(): PokemonAbilityUiModel = PokemonAbilityUiModel(
    abilityName = abilityName,
    slotNumber = slot,
    isHidden = isHidden,
    ability = ability?.mapToUiModel(),
)
