package com.imysko.pokemontesttaskvk.domain.usecase

import com.imysko.pokemontesttaskvk.domain.repositories.AbilityRepository
import javax.inject.Inject

interface LoadAbilityListByNamesUseCase {

    suspend operator fun invoke(names: List<String>)
}

internal class LoadAbilityListByNamesUseCaseImpl @Inject constructor(
    private val abilityRepository: AbilityRepository,
) : LoadAbilityListByNamesUseCase {

    override suspend operator fun invoke(names: List<String>) {
        abilityRepository.loadAbilityListByNames(names)
    }
}
