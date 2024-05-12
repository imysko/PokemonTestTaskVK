package com.imysko.pokemontesttaskvk.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "pokemon_type",
    primaryKeys = [
        "pokemonId",
        "typeName"
    ],
)
data class PokemonTypeEntity(
    val pokemonId: Int,
    val typeName: String,
    @ColumnInfo("slotNumber")
    val slot: Int,
)
