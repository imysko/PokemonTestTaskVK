package com.imysko.pokemontesttaskvk.data.mappers

import com.imysko.pokemontesttaskvk.data.local.entities.PokemonTypeEntity
import com.imysko.pokemontesttaskvk.data.remote.entities.pokemon.PokemonTypeResponse
import com.imysko.pokemontesttaskvk.domain.entities.PokemonType

fun PokemonTypeResponse.mapToDomain(): PokemonType = PokemonType(
    slotNumber = slot,
    name = type.name,
)

fun PokemonTypeEntity.mapToDomain(): PokemonType = PokemonType(
    slotNumber = slot,
    name = typeName,
)

fun PokemonType.mapToEntity(pokemonId: Int): PokemonTypeEntity = PokemonTypeEntity(
    pokemonId = pokemonId,
    slot = slotNumber,
    typeName = name,
)
