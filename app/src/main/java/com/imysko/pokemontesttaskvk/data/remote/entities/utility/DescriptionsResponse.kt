package com.imysko.pokemontesttaskvk.data.remote.entities.utility

import com.google.gson.annotations.SerializedName
import com.imysko.pokemontesttaskvk.data.remote.entities.resource.NamedApiResource

data class DescriptionsResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("language")
    val language: NamedApiResource,
)
