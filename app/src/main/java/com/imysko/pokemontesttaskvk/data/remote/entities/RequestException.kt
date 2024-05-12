package com.imysko.pokemontesttaskvk.data.remote.entities

class RequestException(
    val statusCode: Int,
    message: String,
) : Throwable(message)
