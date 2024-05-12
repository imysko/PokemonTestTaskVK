package com.imysko.pokemontesttaskvk.di

import android.content.Context
import androidx.room.Room
import com.imysko.pokemontesttaskvk.data.local.PokemonDatabase
import com.imysko.pokemontesttaskvk.data.local.dao.AbilityDao
import com.imysko.pokemontesttaskvk.data.local.dao.PokemonAbilityDao
import com.imysko.pokemontesttaskvk.data.local.dao.PokemonDao
import com.imysko.pokemontesttaskvk.data.local.dao.PokemonTypeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Named("AppDatabaseName")
    fun provideAppDatabaseName() = "pokemon.db"

    @Provides
    @Named("AppDatabase")
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        @Named("AppDatabaseName") databaseName: String
    ): PokemonDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = PokemonDatabase::class.java,
            name = databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(
        @Named("AppDatabase") appDatabase: PokemonDatabase
    ): PokemonDao {
        return appDatabase.pokemonDao
    }

    @Provides
    @Singleton
    fun providePokemonAbilityDao(
        @Named("AppDatabase") appDatabase: PokemonDatabase
    ): PokemonAbilityDao {
        return appDatabase.pokemonAbilityDao
    }

    @Provides
    @Singleton
    fun providePokemonTypeDao(
        @Named("AppDatabase") appDatabase: PokemonDatabase
    ): PokemonTypeDao {
        return appDatabase.pokemonTypeDao
    }

    @Provides
    @Singleton
    fun provideAbilityDao(
        @Named("AppDatabase") appDatabase: PokemonDatabase
    ): AbilityDao {
        return appDatabase.abilityDao
    }
}
