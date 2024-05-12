package com.imysko.pokemontesttaskvk.data.remote.services

import com.imysko.pokemontesttaskvk.data.remote.entities.pokemon.PokemonResponse
import com.imysko.pokemontesttaskvk.data.remote.entities.resource.NamedApiResourceList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("/api/v2/pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String,
    ): Response<PokemonResponse>

    @GET("/api/v2/pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Response<NamedApiResourceList>
}
