package com.imysko.pokemontesttaskvk.presentation.entities

data class PokemonUiModel(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val abilities: List<PokemonAbilityUiModel>,
    val types: List<PokemonTypeUiModel>,
    val crySoundUrl: String,
    val logoUrl: String?,
    val imagesUrl: List<String>,
)
