package com.imysko.pokemontesttaskvk.domain.entities

data class PokemonAbility(
    val slot: Int,
    val isHidden: Boolean,
    val abilityName: String,
    val ability: Ability? = null,
)
