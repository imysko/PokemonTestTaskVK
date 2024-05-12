package com.imysko.pokemontesttaskvk.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "pokemon_ability",
    primaryKeys = [
        "pokemonId",
        "abilityName"
    ],
)
data class PokemonAbilityEntity(
    val pokemonId: Int,
    val abilityName: String,
    @ColumnInfo("slotNumber")
    val slot: Int,
    @ColumnInfo("isHidden")
    val isHidden: Boolean,
)
