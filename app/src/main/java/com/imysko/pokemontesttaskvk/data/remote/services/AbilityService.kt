package com.imysko.pokemontesttaskvk.data.remote.services

import com.imysko.pokemontesttaskvk.data.remote.entities.ability.AbilityResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AbilityService {

    @GET("/api/v2/ability/{name}")
    suspend fun getAbilityByName(
        @Path("name") name: String,
    ): Response<AbilityResponse>
}
