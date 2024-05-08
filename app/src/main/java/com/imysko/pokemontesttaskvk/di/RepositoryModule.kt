package com.imysko.pokemontesttaskvk.di

import com.imysko.pokemontesttaskvk.data.repositories.PokemonRepositoryImpl
import com.imysko.pokemontesttaskvk.domain.repositories.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindPokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl,
    ): PokemonRepository
}
