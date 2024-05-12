package com.imysko.pokemontesttaskvk.data.repositories

import com.imysko.pokemontesttaskvk.data.local.dao.PokemonAbilityDao
import com.imysko.pokemontesttaskvk.data.local.dao.PokemonDao
import com.imysko.pokemontesttaskvk.data.local.dao.PokemonTypeDao
import com.imysko.pokemontesttaskvk.data.mappers.mapToDomain
import com.imysko.pokemontesttaskvk.data.mappers.mapToEntity
import com.imysko.pokemontesttaskvk.data.remote.entities.RequestException
import com.imysko.pokemontesttaskvk.data.remote.services.PokemonService
import com.imysko.pokemontesttaskvk.domain.entities.Pokemon
import com.imysko.pokemontesttaskvk.domain.entities.PokemonAbility
import com.imysko.pokemontesttaskvk.domain.repositories.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.internal.http.HTTP_NOT_FOUND
import javax.inject.Inject

internal class PokemonRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDao: PokemonDao,
    private val pokemonAbilityDao: PokemonAbilityDao,
    private val pokemonTypeDao: PokemonTypeDao,
) : PokemonRepository {

    override fun getPokemonById(id: Int): Pokemon? {
        return pokemonDao.getByIdWithPokemonAbilities(id)?.mapToDomain()
    }

    override suspend fun loadPokemonNamesList(offset: Int, limit: Int) {
        val apiResponse = pokemonService.getPokemonList(offset, limit)

        val names = apiResponse.body()?.let { resultList ->
            resultList.results.map { it.name }
        } ?: run {
            throw RequestException(
                statusCode = apiResponse.code(),
                message = apiResponse.message(),
            )
        }

        if (names.isEmpty()) {
            throw RequestException(
                statusCode = HTTP_NOT_FOUND,
                message = apiResponse.message(),
            )
        }

        val pokemonList = names.map { name ->
            pokemonService.getPokemonByName(name).body()?.mapToDomain()
                ?: run {
                    throw RequestException(
                        statusCode = apiResponse.code(),
                        message = apiResponse.message(),
                    )
                }
        }

        pokemonDao.upsert(*pokemonList.map { it.mapToEntity() }.toTypedArray())

        CoroutineScope(Dispatchers.IO).launch {
            pokemonList.forEach { pokemon ->
                val pokemonAbilities = pokemon.abilities.map {
                    it.mapToEntity(pokemon.id)
                }.toTypedArray()

                val pokemonTypes = pokemon.types.map {
                    it.mapToEntity(pokemon.id)
                }.toTypedArray()

                pokemonAbilityDao.upsert(*pokemonAbilities)
                pokemonTypeDao.upsert(*pokemonTypes)
            }
        }
    }

    override fun getPokemonList(): Flow<List<Pokemon>> {
        return pokemonDao.getAll().map { list ->
            list.map { it.mapToDomain() }
        }
    }

    override fun getPokemonAbilities(id: Int): Flow<List<PokemonAbility>> {
        return pokemonAbilityDao.getByPokemonIdWithAbility(id).map { list ->
            list.map { it.mapToDomain() }.filter { it.ability != null }
        }
    }
}
