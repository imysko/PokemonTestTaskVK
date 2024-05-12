package com.imysko.pokemontesttaskvk.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "pokemon",
)
data class PokemonEntity(
    @PrimaryKey
    val pokemonId: Int,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("height")
    val height: Int,
    @ColumnInfo("weight")
    val weight: Int,
    @ColumnInfo("crySoundUrl")
    val crySoundUrl: String,
    @ColumnInfo("logoUrl")
    val logoUrl: String?,
    @ColumnInfo("imagesUrl")
    val imagesUrl: List<String>,
)
