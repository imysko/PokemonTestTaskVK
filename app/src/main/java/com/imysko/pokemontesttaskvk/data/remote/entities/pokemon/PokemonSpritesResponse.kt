package com.imysko.pokemontesttaskvk.data.remote.entities.pokemon

import com.google.gson.annotations.JsonAdapter
import com.imysko.pokemontesttaskvk.data.utils.PokemonSpritesResponseJsonAdapter

@JsonAdapter(PokemonSpritesResponseJsonAdapter::class)
data class PokemonSpritesResponse(
    val logo: String?,
    val images: List<String>,
)
