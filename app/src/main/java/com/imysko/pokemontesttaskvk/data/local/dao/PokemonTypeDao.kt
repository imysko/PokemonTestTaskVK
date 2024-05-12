package com.imysko.pokemontesttaskvk.data.local.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.imysko.pokemontesttaskvk.data.local.entities.PokemonTypeEntity

@Dao
interface PokemonTypeDao {

    @Upsert
    fun upsert(vararg pokemonTypeEntity: PokemonTypeEntity)
}