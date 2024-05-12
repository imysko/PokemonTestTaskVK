package com.imysko.pokemontesttaskvk.data.repositories

import com.imysko.pokemontesttaskvk.data.local.dao.AbilityDao
import com.imysko.pokemontesttaskvk.data.mappers.mapToDomain
import com.imysko.pokemontesttaskvk.data.mappers.mapToEntity
import com.imysko.pokemontesttaskvk.data.remote.entities.RequestException
import com.imysko.pokemontesttaskvk.data.remote.services.AbilityService
import com.imysko.pokemontesttaskvk.domain.repositories.AbilityRepository
import javax.inject.Inject

internal class AbilityRepositoryImpl @Inject constructor(
    private val abilityService: AbilityService,
    private val abilityDao: AbilityDao,
) : AbilityRepository {

    override suspend fun loadAbilityListByNames(names: List<String>) {
        val abilities = names.map { name ->
            val apiResponse = abilityService.getAbilityByName(name)

            apiResponse.body()?.mapToDomain()?.mapToEntity() ?: run {
                throw RequestException(
                    statusCode = apiResponse.code(),
                    message = apiResponse.message(),
                )
            }
        }.toTypedArray()

        abilityDao.upsert(*abilities)
    }
}
