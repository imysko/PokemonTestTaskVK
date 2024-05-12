package com.imysko.pokemontesttaskvk.presentation.entities

data class PokemonAbilityUiModel(
    val slotNumber: Int,
    val isHidden: Boolean,
    val abilityName: String,
    val ability: AbilityUiModel? = null,
)
