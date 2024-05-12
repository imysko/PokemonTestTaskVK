package com.imysko.pokemontesttaskvk.data.local.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.imysko.pokemontesttaskvk.data.local.entities.AbilityEntity

@Dao
interface AbilityDao {

    @Upsert
    suspend fun upsert(vararg ability: AbilityEntity)
}
