package com.imysko.pokemontesttaskvk.domain.usecase

import com.imysko.pokemontesttaskvk.domain.entities.Pokemon
import com.imysko.pokemontesttaskvk.domain.repositories.PokemonRepository
import javax.inject.Inject

interface GetPokemonByNameUseCase {

    suspend operator fun invoke(
        name: String,
    ): Result<Pokemon>
}

internal class GetPokemonByNameUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : GetPokemonByNameUseCase {

    override suspend operator fun invoke(
        name: String,
    ): Result<Pokemon> = pokemonRepository.getPokemonByName(name)
}
