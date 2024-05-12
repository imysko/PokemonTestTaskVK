package com.imysko.pokemontesttaskvk.data.remote.entities.ability

import com.google.gson.annotations.SerializedName

data class AbilityResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("effect_entries")
    val effectEntries: List<VerboseEffectResponse>,
)
