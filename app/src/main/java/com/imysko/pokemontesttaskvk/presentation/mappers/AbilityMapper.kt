package com.imysko.pokemontesttaskvk.presentation.mappers

import com.imysko.pokemontesttaskvk.domain.entities.Ability
import com.imysko.pokemontesttaskvk.presentation.entities.AbilityUiModel

fun Ability.mapToUiModel(): AbilityUiModel = AbilityUiModel(
    id = id,
    name = name.replaceFirstChar { it.uppercase() }
        .replace('-', ' '),
    description = description,
)
