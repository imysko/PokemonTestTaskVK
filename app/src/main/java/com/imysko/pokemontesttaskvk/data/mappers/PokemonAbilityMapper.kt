package com.imysko.pokemontesttaskvk.data.mappers

import com.imysko.pokemontesttaskvk.data.local.entities.PokemonAbilitiesWithAbility
import com.imysko.pokemontesttaskvk.data.local.entities.PokemonAbilityEntity
import com.imysko.pokemontesttaskvk.data.remote.entities.pokemon.PokemonAbilityResponse
import com.imysko.pokemontesttaskvk.domain.entities.PokemonAbility

fun PokemonAbilityResponse.mapToDomain(): PokemonAbility = PokemonAbility(
    slot = slot,
    isHidden = isHidden,
    abilityName = ability.name,
)

fun PokemonAbilityEntity.mapToDomain(): PokemonAbility = PokemonAbility(
    abilityName = abilityName,
    slot = slot,
    isHidden = isHidden,
)

fun PokemonAbility.mapToEntity(pokemonId: Int): PokemonAbilityEntity = PokemonAbilityEntity(
    abilityName = abilityName,
    slot = slot,
    isHidden = isHidden,
    pokemonId = pokemonId,
)

fun PokemonAbilitiesWithAbility.mapToDomain(): PokemonAbility = PokemonAbility(
    abilityName = pokemonAbility.abilityName,
    slot = pokemonAbility.slot,
    isHidden = pokemonAbility.isHidden,
    ability = ability?.mapToDomain(),
)
