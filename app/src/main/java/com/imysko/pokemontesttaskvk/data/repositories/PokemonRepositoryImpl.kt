package com.imysko.pokemontesttaskvk.data.repositories

import com.imysko.pokemontesttaskvk.data.entities.RequestException
import com.imysko.pokemontesttaskvk.data.mappers.mapToDomain
import com.imysko.pokemontesttaskvk.data.services.PokemonService
import com.imysko.pokemontesttaskvk.domain.entities.Pokemon
import com.imysko.pokemontesttaskvk.domain.repositories.PokemonRepository
import javax.inject.Inject

internal class PokemonRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonService,
) : BaseRepository(), PokemonRepository {

    private val cashedList: MutableList<Pokemon> = mutableListOf()

    override suspend fun getPokemonList(offset: Int, limit: Int): Result<List<Pokemon>> {
//        return apiCall(
//            call = { pokemonService.getPokemonList(offset, limit) },
//            mappingResult = { resourceList ->
//                resourceList.results.mapNotNull { getPokemonByName(it.name).getOrNull() }
//            },
//        )
        val apiResponse = pokemonService.getPokemonList(offset, limit)

        return apiResponse.body()?.let { resourceList ->
            val result = resourceList.results.mapNotNull { resource ->
                getPokemonByName(resource.name).getOrNull()?.also { pokemon ->
                    if (cashedList.none { it.id == pokemon.id }) {
                        cashedList.add(pokemon)
                    }
                }
            }

            Result.success(result)
        } ?: run {
            Result.failure(
                RequestException(
                    statusCode = apiResponse.code(),
                    message = apiResponse.message(),
                )
            )
        }
    }

    override suspend fun getPokemonById(id: Int): Result<Pokemon> {
        return cashedList.find { it.id == id }?.let {
            Result.success(it)
        } ?: apiCall(
            call = { pokemonService.getPokemonById(id) },
            mappingResult = { it.mapToDomain() },
        )
    }

    override suspend fun getPokemonByName(name: String): Result<Pokemon> {
        val apiResponse = pokemonService.getPokemonByName(name)

        return apiResponse.body()?.let { pokemonResponse ->
            Result.success(pokemonResponse.mapToDomain())
        } ?: run {
            Result.failure(
                RequestException(
                    statusCode = apiResponse.code(),
                    message = apiResponse.message(),
                )
            )
        }
    }
}
