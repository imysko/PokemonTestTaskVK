package com.imysko.pokemontesttaskvk.domain.usecase

import com.imysko.pokemontesttaskvk.domain.entities.Pokemon
import com.imysko.pokemontesttaskvk.domain.repositories.PokemonRepository
import javax.inject.Inject

interface GetPokemonListUseCase {

    suspend operator fun invoke(
        offset: Int = 0,
        limit: Int = 20,
    ): Result<List<Pokemon>>
}

internal class GetPokemonListUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : GetPokemonListUseCase {

    override suspend operator fun invoke(
        offset: Int,
        limit: Int,
    ): Result<List<Pokemon>> = pokemonRepository.getPokemonList(offset, limit)
}
