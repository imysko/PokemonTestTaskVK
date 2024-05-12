package com.imysko.pokemontesttaskvk.data.remote.entities.type

import com.google.gson.annotations.SerializedName
import com.imysko.pokemontesttaskvk.data.remote.entities.resource.NamedApiResource

data class TypeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("move_damage_class")
    val moveDamageClass: NamedApiResource,
)
