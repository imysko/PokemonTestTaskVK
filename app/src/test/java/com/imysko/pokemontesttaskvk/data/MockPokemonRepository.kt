package com.imysko.pokemontesttaskvk.data

import com.imysko.pokemontesttaskvk.domain.entities.Pokemon
import com.imysko.pokemontesttaskvk.domain.entities.PokemonAbility
import com.imysko.pokemontesttaskvk.domain.entities.PokemonType
import com.imysko.pokemontesttaskvk.domain.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class MockPokemonRepository : PokemonRepository {

    override fun getPokemonById(id: Int): Pokemon? {
        return mockPokemonList().find { it.id == id }
    }

    override suspend fun loadPokemonNamesList(offset: Int, limit: Int) {
        TODO("Not yet implemented")
    }

    override fun getPokemonList(): Flow<List<Pokemon>> {
        return flowOf(mockPokemonList())
    }

    override fun getPokemonAbilities(id: Int): Flow<List<PokemonAbility>> {
        return flowOf(mockPokemonList().find { it.id == id }?.abilities ?: emptyList())
    }

    private fun mockPokemonList(): List<Pokemon> = listOf(
        Pokemon(
            id = 1,
            name = "bulbasaur",
            height = 4,
            weight = 60,
            abilities = listOf(
                PokemonAbility(
                    slot = 1,
                    isHidden = false,
                    abilityName = "overgrow",
                ),
                PokemonAbility(
                    slot = 3,
                    isHidden = true,
                    abilityName = "chlorophyll",
                ),
            ),
            types = listOf(
                PokemonType(
                    slotNumber = 1,
                    name = "grass",
                ),
                PokemonType(
                    slotNumber = 2,
                    name = "poison",
                ),
            ),
            crySoundUrl = "https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/latest/1.ogg",
            logoUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg",
            imagesUrl = listOf(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/1.png",
            ),
        ),
        Pokemon(
            id = 25,
            name = "pikachu",
            height = 4,
            weight = 60,
            abilities = listOf(
                PokemonAbility(
                    slot = 1,
                    isHidden = false,
                    abilityName = "static",
                ),
                PokemonAbility(
                    slot = 3,
                    isHidden = true,
                    abilityName = "lightning-rod",
                ),
            ),
            types = listOf(
                PokemonType(
                    slotNumber = 1,
                    name = "electric",
                ),
            ),
            crySoundUrl = "https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/latest/25.ogg",
            logoUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/25.svg",
            imagesUrl = listOf(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/25.svg",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/25.png",
            ),
        ),
    )
}