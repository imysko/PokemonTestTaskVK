package com.imysko.pokemontesttaskvk.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.imysko.pokemontesttaskvk.data.local.entities.PokemonAbilitiesWithAbility
import com.imysko.pokemontesttaskvk.data.local.entities.PokemonAbilityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonAbilityDao {

    @Upsert
    fun upsert(vararg pokemonAbilityEntity: PokemonAbilityEntity)

    @Transaction
    @Query("SELECT * FROM pokemon_ability WHERE pokemonId=:id ORDER BY slotNumber")
    fun getByPokemonIdWithAbility(id: Int): Flow<List<PokemonAbilitiesWithAbility>>
}
