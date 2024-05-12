package com.imysko.pokemontesttaskvk.domain.usecase

import com.imysko.pokemontesttaskvk.domain.repositories.PokemonRepository
import javax.inject.Inject

interface LoadPokemonNamesListUseCase {

    suspend operator fun invoke(
        offset: Int,
        limit: Int,
    )
}

internal class LoadPokemonNamesListUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : LoadPokemonNamesListUseCase {

    override suspend operator fun invoke(
        offset: Int,
        limit: Int,
    ) = pokemonRepository.loadPokemonNamesList(offset, limit)
}
