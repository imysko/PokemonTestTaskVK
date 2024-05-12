package com.imysko.pokemontesttaskvk.presentation.ui.components.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.imysko.pokemontesttaskvk.R
import com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonDetail.AbilityListUiState
import com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonDetail.PokemonDetailScreenEvent

@Composable
fun PokemonAbilityList(
    abilityListUiState: AbilityListUiState,
    count: Int,
    onEvent: (PokemonDetailScreenEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        Text(
            text = stringResource(id = R.string.abilities),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.SemiBold,
            ),
        )

        when (abilityListUiState) {
            is AbilityListUiState.ShowAbilityList -> {
                abilityListUiState.abilityList.forEach { pokemonAbility ->
                    pokemonAbility.ability?.let { ability ->
                        PokemonAbilityItem(
                            slotNumber = pokemonAbility.slotNumber,
                            isHidden = pokemonAbility.isHidden,
                            ability = ability,
                        )
                    }
                }
            }

            AbilityListUiState.OnLoading -> {
                repeat(count) {
                    PokemonAbilityItemPlaceholder()
                }
            }

            AbilityListUiState.OnError -> {
                AbilityListPlaceHolder(
                    message = stringResource(id = R.string.error),
                    onEvent = onEvent,
                )
            }

            AbilityListUiState.NoInternetConnection -> {
                AbilityListPlaceHolder(
                    message = stringResource(id = R.string.no_internet_connection),
                    onEvent = onEvent,
                )
            }
        }
    }
}


@Composable
private fun AbilityListPlaceHolder(
    message: String,
    onEvent: (PokemonDetailScreenEvent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
            ),
        )

        OutlinedButton(
            onClick = { onEvent(PokemonDetailScreenEvent.OnReloadAbilityList) },
            contentPadding = PaddingValues(horizontal = 10.dp),
            content = {
                Text(
                    text = stringResource(id = R.string.try_again),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Medium,
                    ),
                )
            },
        )
    }
}
