package com.imysko.pokemontesttaskvk.data.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.imysko.pokemontesttaskvk.data.remote.entities.pokemon.PokemonSpritesResponse
import java.lang.reflect.Type

class PokemonSpritesResponseJsonAdapter : JsonDeserializer<PokemonSpritesResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PokemonSpritesResponse {

        val members = listOf(
            "dream_world",
            "official-artwork",
            "showdown",
        )


        val urls = mutableListOf<String>()

        json?.asJsonObject?.getAsJsonObject("other")?.let { jsonObject ->
            jsonObject.keySet()
                ?.filter { it in members }
                ?.map { jsonObject.getAsJsonObject(it) }
                ?.map { childJsonObject ->
                    childJsonObject?.keySet()
                        ?.filter { childJsonObject.get(it).isJsonPrimitive }
                        ?.map { childJsonObject.getAsJsonPrimitive(it).asString }
                }?.forEach {
                    it?.let { urls.addAll(it) }
                }
        }

        return PokemonSpritesResponse(
            logo = urls.firstOrNull(),
            images = urls
        )
    }
}
