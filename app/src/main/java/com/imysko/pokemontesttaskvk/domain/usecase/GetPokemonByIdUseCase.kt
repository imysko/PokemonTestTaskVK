package com.imysko.pokemontesttaskvk.domain.usecase

import com.imysko.pokemontesttaskvk.domain.entities.Pokemon
import com.imysko.pokemontesttaskvk.domain.repositories.PokemonRepository
import javax.inject.Inject

interface GetPokemonByIdUseCase {

    operator fun invoke(
        id: Int,
    ): Pokemon?
}

internal class GetPokemonByIdUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : GetPokemonByIdUseCase {

    override operator fun invoke(
        id: Int,
    ): Pokemon? = pokemonRepository.getPokemonById(id)
}
