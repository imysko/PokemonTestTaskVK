package com.imysko.pokemontesttaskvk.domain.usecase

import com.imysko.pokemontesttaskvk.domain.entities.PokemonAbility
import com.imysko.pokemontesttaskvk.domain.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPokemonAbilityListUseCase {

    operator fun invoke(id: Int): Flow<List<PokemonAbility>>
}

internal class GetPokemonAbilityListUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : GetPokemonAbilityListUseCase {

    override operator fun invoke(id: Int): Flow<List<PokemonAbility>> {
        return pokemonRepository.getPokemonAbilities(id)
    }
}
