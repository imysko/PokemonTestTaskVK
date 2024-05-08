package com.imysko.pokemontesttaskvk.data.mappers

import com.imysko.pokemontesttaskvk.data.entities.remote.PokemonResponse
import com.imysko.pokemontesttaskvk.domain.entities.Pokemon

fun PokemonResponse.mapToDomain(): Pokemon = Pokemon(
    id = id,
    name = name,
    height = height,
    weight = weight,
    crySoundUrl = cries.latest,
    logoUrl = sprites.logo,
    imagesUrl = sprites.images,
)
