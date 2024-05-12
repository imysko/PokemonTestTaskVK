package com.imysko.pokemontesttaskvk.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonAbilitiesWithAbility(
    @Embedded
    val pokemonAbility: PokemonAbilityEntity,
    @Relation(
        parentColumn = "abilityName",
        entityColumn = "name",
    )
    val ability: AbilityEntity?,
)
