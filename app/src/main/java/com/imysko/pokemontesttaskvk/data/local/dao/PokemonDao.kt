package com.imysko.pokemontesttaskvk.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.imysko.pokemontesttaskvk.data.local.entities.PokemonEntity
import com.imysko.pokemontesttaskvk.data.local.entities.PokemonWithPokemonAbilitiesAndTypes
import com.imysko.pokemontesttaskvk.data.local.entities.PokemonWithPokemonTypes
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Upsert
    fun upsert(vararg pokemon: PokemonEntity)

    @Transaction
    @Query("SELECT * FROM pokemon WHERE pokemonId=:id")
    fun getByIdWithPokemonAbilities(id: Int): PokemonWithPokemonAbilitiesAndTypes?

    @Transaction
    @Query("SELECT * FROM pokemon")
    fun getAll(): Flow<List<PokemonWithPokemonTypes>>
}
