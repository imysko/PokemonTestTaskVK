package com.imysko.pokemontesttaskvk.data.repositories

import com.imysko.pokemontesttaskvk.data.entities.RequestException
import retrofit2.Response

abstract class BaseRepository {

    suspend fun <T, P> apiCall(
        call: suspend () -> Response<T>,
        mappingResult: (T) -> P,
    ): Result<P> {
        call().also { apiResponse ->
            return apiResponse.body()?.let {
                Result.success(mappingResult(it))
            } ?: run {
                Result.failure(
                    RequestException(
                        statusCode = apiResponse.code(),
                        message = apiResponse.message(),
                    )
                )
            }
        }
    }
}