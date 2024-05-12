package com.imysko.pokemontesttaskvk.data.remote.entities.pokemon

import com.google.gson.annotations.SerializedName
import com.imysko.pokemontesttaskvk.data.remote.entities.resource.NamedApiResource

data class PokemonTypeResponse(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: NamedApiResource,
)
