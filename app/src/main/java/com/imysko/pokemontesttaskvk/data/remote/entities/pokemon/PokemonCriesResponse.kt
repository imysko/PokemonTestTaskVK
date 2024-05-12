package com.imysko.pokemontesttaskvk.data.remote.entities.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonCriesResponse(
    @SerializedName("latest")
    val latest: String,
    @SerializedName("legacy")
    val legacy: String,
)
