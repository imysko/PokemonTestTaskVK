package com.imysko.pokemontesttaskvk.data.remote.entities.resource

import com.google.gson.annotations.SerializedName

data class NamedApiResourceList(
    @SerializedName("count")
    val totalCount: Int,
    @SerializedName("next")
    val nextUrl: String?,
    @SerializedName("previous")
    val previousUrl: String?,
    @SerializedName("results")
    val results: List<NamedApiResource>,
)
