package com.imysko.pokemontesttaskvk.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.imysko.pokemontesttaskvk.data.local.dao.AbilityDao
import com.imysko.pokemontesttaskvk.data.local.dao.PokemonAbilityDao
import com.imysko.pokemontesttaskvk.data.local.dao.PokemonDao
import com.imysko.pokemontesttaskvk.data.local.dao.PokemonTypeDao
import com.imysko.pokemontesttaskvk.data.local.entities.AbilityEntity
import com.imysko.pokemontesttaskvk.data.local.entities.PokemonAbilityEntity
import com.imysko.pokemontesttaskvk.data.local.entities.PokemonEntity
import com.imysko.pokemontesttaskvk.data.local.entities.PokemonTypeEntity

@Database(
    entities = [
        PokemonEntity::class,
        PokemonAbilityEntity::class,
        PokemonTypeEntity::class,
        AbilityEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(DatabaseConvertors::class)
abstract class PokemonDatabase : RoomDatabase() {

    abstract val pokemonDao: PokemonDao
    abstract val pokemonAbilityDao: PokemonAbilityDao
    abstract val pokemonTypeDao: PokemonTypeDao

    abstract val abilityDao: AbilityDao
}
