package com.imysko.pokemontesttaskvk.data.entities.remote

import com.google.gson.annotations.JsonAdapter
import com.imysko.pokemontesttaskvk.data.utils.PokemonSpritesResponseJsonAdapter

@JsonAdapter(PokemonSpritesResponseJsonAdapter::class)
data class PokemonSpritesResponse(
    val logo: String?,
    val images: List<String>,
)
