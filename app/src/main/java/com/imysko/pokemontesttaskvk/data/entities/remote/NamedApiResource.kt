package com.imysko.pokemontesttaskvk.data.entities.remote

import com.google.gson.annotations.SerializedName

data class NamedApiResource(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
)
