package com.imysko.pokemontesttaskvk.data.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.imysko.pokemontesttaskvk.data.entities.remote.PokemonSpritesResponse
import java.lang.reflect.Type

class PokemonSpritesResponseJsonAdapter : JsonDeserializer<PokemonSpritesResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PokemonSpritesResponse {

//        val dreamWorld = json?.asJsonObject?.getAsJsonObject("other")
//            ?.getAsJsonObject("dream_world")
//        val officialArtwork = json?.asJsonObject?.getAsJsonObject("other")
//            ?.getAsJsonObject("official-artwork")


        val members = listOf(
            "dream_world",
            "official-artwork",
//            "showdown",
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

//        return PokemonSpritesResponse(
//            dreamWorldFrontDefault = dreamWorld?.getAsJsonPrimitive("front_default")?.asString,
//            dreamWorldFrontFemale = dreamWorld?.getAsJsonPrimitive("front_female")?.asString,
//            officialArtworkFrontDefault = officialArtwork?.getAsJsonPrimitive("front_default")?.asString,
//            officialArtworkFrontShiny = officialArtwork?.getAsJsonPrimitive("front_shiny")?.asString,
//        )
    }
}
