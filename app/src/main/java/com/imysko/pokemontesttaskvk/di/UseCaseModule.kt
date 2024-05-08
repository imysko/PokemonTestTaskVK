package com.imysko.pokemontesttaskvk.di

import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonByIdUseCase
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonByIdUseCaseImpl
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonByNameUseCase
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonByNameUseCaseImpl
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonListUseCase
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UseCaseModule {

    @Binds
    @Singleton
    internal abstract fun bindGetPokemonByNameUseCase(
        getPokemonByNameUseCaseImpl: GetPokemonByNameUseCaseImpl,
    ): GetPokemonByNameUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetPokemonByIdUseCase(
        getPokemonByIdUseCaseImpl: GetPokemonByIdUseCaseImpl,
    ): GetPokemonByIdUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetPokemonListUseCase(
        getPokemonListUseCaseImpl: GetPokemonListUseCaseImpl,
    ): GetPokemonListUseCase
}
