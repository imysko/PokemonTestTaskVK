package com.imysko.pokemontesttaskvk.di

import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonAbilityListUseCase
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonAbilityListUseCaseImpl
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonByIdUseCase
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonByIdUseCaseImpl
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonListUseCase
import com.imysko.pokemontesttaskvk.domain.usecase.GetPokemonListUseCaseImpl
import com.imysko.pokemontesttaskvk.domain.usecase.LoadAbilityListByNamesUseCase
import com.imysko.pokemontesttaskvk.domain.usecase.LoadAbilityListByNamesUseCaseImpl
import com.imysko.pokemontesttaskvk.domain.usecase.LoadPokemonNamesListUseCase
import com.imysko.pokemontesttaskvk.domain.usecase.LoadPokemonNamesListUseCaseImpl
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
    internal abstract fun bindGetPokemonByIdUseCase(
        getPokemonByIdUseCaseImpl: GetPokemonByIdUseCaseImpl,
    ): GetPokemonByIdUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetPokemonListUseCase(
        getPokemonListUseCaseImpl: GetPokemonListUseCaseImpl,
    ): GetPokemonListUseCase

    @Binds
    @Singleton
    internal abstract fun bindLoadPokemonNamesListUseCase(
        loadPokemonNamesListUseCaseImpl: LoadPokemonNamesListUseCaseImpl,
    ): LoadPokemonNamesListUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetPokemonAbilityListUseCase(
        getPokemonAbilityListUseCaseImpl: GetPokemonAbilityListUseCaseImpl,
    ): GetPokemonAbilityListUseCase

    @Binds
    @Singleton
    internal abstract fun bindLoadAbilityListByNamesUseCase(
        loadAbilityListByNamesUseCaseImpl: LoadAbilityListByNamesUseCaseImpl,
    ): LoadAbilityListByNamesUseCase
}
