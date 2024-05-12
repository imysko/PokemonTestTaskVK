package com.imysko.pokemontesttaskvk.data.remote.entities.type

import com.google.gson.annotations.SerializedName
import com.imysko.pokemontesttaskvk.data.remote.entities.utility.DescriptionsResponse

data class MoveDamageClassResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("descriptions")
    val descriptions: List<DescriptionsResponse>,
)
