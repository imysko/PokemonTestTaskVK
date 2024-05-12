package com.imysko.pokemontesttaskvk.data.remote.entities.ability

import com.google.gson.annotations.SerializedName
import com.imysko.pokemontesttaskvk.data.remote.entities.resource.NamedApiResource

data class VerboseEffectResponse(
    @SerializedName("short_effect")
    val shortEffect: String,
    @SerializedName("language")
    val language: NamedApiResource,
)
