package com.imysko.pokemontesttaskvk.data.entities

class RequestException(
    val statusCode: Int,
    message: String,
) : Throwable(message)
