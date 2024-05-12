package com.imysko.pokemontesttaskvk.data.remote.entities.pokemon

import com.google.gson.annotations.SerializedName
import com.imysko.pokemontesttaskvk.data.remote.entities.resource.NamedApiResource

data class PokemonAbilityResponse(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("ability")
    val ability: NamedApiResource,
)
