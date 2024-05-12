package com.imysko.pokemontesttaskvk.data.remote.entities.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("cries")
    val cries: PokemonCriesResponse,
    @SerializedName("sprites")
    val sprites: PokemonSpritesResponse,
    @SerializedName("abilities")
    val abilities: List<PokemonAbilityResponse>,
    @SerializedName("types")
    val types: List<PokemonTypeResponse>,
)
