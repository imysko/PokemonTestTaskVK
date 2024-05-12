package com.imysko.pokemontesttaskvk.domain.entities

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val abilities: List<PokemonAbility>,
    val types: List<PokemonType>,
    val crySoundUrl: String,
    val logoUrl: String?,
    val imagesUrl: List<String>,
)
