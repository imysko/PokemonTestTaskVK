package com.imysko.pokemontesttaskvk.domain.usecase

import com.imysko.pokemontesttaskvk.domain.entities.Pokemon
import com.imysko.pokemontesttaskvk.domain.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPokemonListUseCase {

    operator fun invoke(): Flow<List<Pokemon>>
}

internal class GetPokemonListUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : GetPokemonListUseCase {

    override operator fun invoke(): Flow<List<Pokemon>> = pokemonRepository.getPokemonList()
}
