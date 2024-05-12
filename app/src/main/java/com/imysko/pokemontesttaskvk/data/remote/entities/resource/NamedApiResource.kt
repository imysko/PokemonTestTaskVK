package com.imysko.pokemontesttaskvk.data.remote.entities.resource

import com.google.gson.annotations.SerializedName

data class NamedApiResource(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
)
