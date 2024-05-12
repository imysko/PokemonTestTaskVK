package com.imysko.pokemontesttaskvk.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "ability",
    primaryKeys = [
        "abilityId",
        "name"
    ]
)
data class AbilityEntity(
    val abilityId: Int,
    val name: String,
    @ColumnInfo("description")
    val description: String,
)
