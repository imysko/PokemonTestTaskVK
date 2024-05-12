package com.imysko.pokemontesttaskvk.domain.repositories

import com.imysko.pokemontesttaskvk.domain.entities.Pokemon
import com.imysko.pokemontesttaskvk.domain.entities.PokemonAbility
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonById(
        id: Int,
    ): Pokemon?

    suspend fun loadPokemonNamesList(
        offset: Int,
        limit: Int,
    )

    fun getPokemonList(): Flow<List<Pokemon>>

    fun getPokemonAbilities(id: Int): Flow<List<PokemonAbility>>
}
