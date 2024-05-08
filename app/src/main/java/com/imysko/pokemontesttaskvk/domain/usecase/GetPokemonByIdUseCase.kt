package com.imysko.pokemontesttaskvk.domain.usecase

import com.imysko.pokemontesttaskvk.domain.entities.Pokemon
import com.imysko.pokemontesttaskvk.domain.repositories.PokemonRepository
import javax.inject.Inject

interface GetPokemonByIdUseCase {

    suspend operator fun invoke(
        id: Int,
    ): Result<Pokemon>
}

internal class GetPokemonByIdUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : GetPokemonByIdUseCase {

    override suspend operator fun invoke(
        id: Int,
    ): Result<Pokemon> = pokemonRepository.getPokemonById(id)
}
