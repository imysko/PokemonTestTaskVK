package com.imysko.pokemontesttaskvk.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonWithPokemonTypes(
    @Embedded
    val pokemon: PokemonEntity,

    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "pokemonId",
    )
    val pokemonTypes: List<PokemonTypeEntity> = emptyList()
)
