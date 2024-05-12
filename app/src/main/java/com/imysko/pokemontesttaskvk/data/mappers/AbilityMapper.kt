package com.imysko.pokemontesttaskvk.data.mappers

import com.imysko.pokemontesttaskvk.data.local.entities.AbilityEntity
import com.imysko.pokemontesttaskvk.data.remote.entities.ability.AbilityResponse
import com.imysko.pokemontesttaskvk.domain.entities.Ability

fun AbilityResponse.mapToDomain(): Ability = Ability(
    id = id,
    name = name,
    description = effectEntries.first { it.language.name == "en" }.shortEffect,
)

fun AbilityEntity.mapToDomain(): Ability = Ability(
    id = abilityId,
    name = name,
    description = description,
)

fun Ability.mapToEntity(): AbilityEntity = AbilityEntity(
    abilityId = id,
    name = name,
    description = description,
)
