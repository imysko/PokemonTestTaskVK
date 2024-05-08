package com.imysko.pokemontesttaskvk.di

import com.imysko.pokemontesttaskvk.data.services.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Provides
    internal fun providerPokemonService(
        @Named("ApiRetrofit") retrofit: Retrofit,
    ): PokemonService = retrofit.create(PokemonService::class.java)
}
