package com.imysko.pokemontesttaskvk.presentation.mappers

import com.imysko.pokemontesttaskvk.domain.entities.Pokemon
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonAbilityUiModel
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonTypeUiModel
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonUiModel

fun Pokemon.mapToUiModel(): PokemonUiModel = PokemonUiModel(
    id = id,
    name = name.replaceFirstChar { it.uppercase() }
        .replace('-', ' '),
    height = height,
    weight = weight,
    abilities = abilities.map { pokemonAbility ->
        PokemonAbilityUiModel(
            slotNumber = pokemonAbility.slot,
            isHidden = pokemonAbility.isHidden,
            abilityName = pokemonAbility.abilityName,
            ability = pokemonAbility.ability?.mapToUiModel(),
        )
    }.sortedBy { it.slotNumber },
    types = types.map { pokemonType ->
        PokemonTypeUiModel(
            slotNumber = pokemonType.slotNumber,
            name = pokemonType.name.replaceFirstChar { it.titlecaseChar() },
        )
    },
    crySoundUrl = crySoundUrl,
    logoUrl = logoUrl,
    imagesUrl = imagesUrl,
)
