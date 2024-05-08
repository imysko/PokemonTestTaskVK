package com.imysko.pokemontesttaskvk.domain.repositories

import com.imysko.pokemontesttaskvk.domain.entities.Pokemon

interface PokemonRepository {

    suspend fun getPokemonList(
        offset: Int,
        limit: Int,
    ): Result<List<Pokemon>>

    suspend fun getPokemonById(
        id: Int,
    ): Result<Pokemon>

    suspend fun getPokemonByName(
        name: String,
    ): Result<Pokemon>
}
