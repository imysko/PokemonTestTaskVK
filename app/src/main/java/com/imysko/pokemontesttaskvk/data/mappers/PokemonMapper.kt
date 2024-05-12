package com.imysko.pokemontesttaskvk.data.mappers

import com.imysko.pokemontesttaskvk.data.local.entities.PokemonEntity
import com.imysko.pokemontesttaskvk.data.local.entities.PokemonWithPokemonAbilitiesAndTypes
import com.imysko.pokemontesttaskvk.data.local.entities.PokemonWithPokemonTypes
import com.imysko.pokemontesttaskvk.data.remote.entities.pokemon.PokemonResponse
import com.imysko.pokemontesttaskvk.domain.entities.Pokemon

fun PokemonResponse.mapToDomain(): Pokemon = Pokemon(
    id = id,
    name = name,
    height = height,
    weight = weight,
    abilities = abilities.map { it.mapToDomain() },
    types = types.map { it.mapToDomain() },
    crySoundUrl = cries.latest,
    logoUrl = sprites.logo,
    imagesUrl = sprites.images,
)

fun PokemonEntity.mapToDomain(): Pokemon = Pokemon(
    id = pokemonId,
    name = name,
    height = height,
    weight = weight,
    abilities = emptyList(),
    types = emptyList(),
    crySoundUrl = crySoundUrl,
    logoUrl = logoUrl,
    imagesUrl = imagesUrl,
)

fun PokemonWithPokemonTypes.mapToDomain(): Pokemon = Pokemon(
    id = pokemon.pokemonId,
    name = pokemon.name,
    height = pokemon.height,
    weight = pokemon.weight,
    abilities = emptyList(),
    types = pokemonTypes.map { it.mapToDomain() },
    crySoundUrl = pokemon.crySoundUrl,
    logoUrl = pokemon.logoUrl,
    imagesUrl = pokemon.imagesUrl,
)

fun PokemonWithPokemonAbilitiesAndTypes.mapToDomain(): Pokemon = Pokemon(
    id = pokemon.pokemonId,
    name = pokemon.name,
    height = pokemon.height,
    weight = pokemon.weight,
    abilities = pokemonAbilities.map { it.mapToDomain() },
    types = pokemonTypes.map { it.mapToDomain() },
    crySoundUrl = pokemon.crySoundUrl,
    logoUrl = pokemon.logoUrl,
    imagesUrl = pokemon.imagesUrl,
)

fun Pokemon.mapToEntity(): PokemonEntity = PokemonEntity(
    pokemonId = id,
    name = name,
    height = height,
    weight = weight,
    crySoundUrl = crySoundUrl,
    logoUrl = logoUrl,
    imagesUrl = imagesUrl,
)
