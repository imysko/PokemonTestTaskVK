package com.imysko.pokemontesttaskvk.domain.repositories

interface AbilityRepository {

    suspend fun loadAbilityListByNames(names: List<String>)
}
