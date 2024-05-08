package com.imysko.pokemontesttaskvk.data.services

import com.imysko.pokemontesttaskvk.data.entities.remote.NamedApiResourceList
import com.imysko.pokemontesttaskvk.data.entities.remote.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("/api/v2/pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id") id: Int,
    ): Response<PokemonResponse>

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
