package com.imysko.pokemontesttaskvk.presentation.ui.components.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PauseCircleOutline
import androidx.compose.material.icons.outlined.PlayCircleOutline
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.imysko.pokemontesttaskvk.R
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonUiModel
import com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonDetail.AbilityListUiState
import com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonDetail.PokemonDetailScreenEvent

@Composable
fun PokemonDetailBody(
    pokemon: PokemonUiModel,
    abilityListUiState: AbilityListUiState?,
    isPlayingSound: Boolean,
    onEvent: (PokemonDetailScreenEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            ImagePager(imagesUrl = pokemon.imagesUrl)
        }
        item {
            Header(
                name = pokemon.name,
                isPlayingSound = isPlayingSound,
                onEvent = onEvent,
            )
        }
        abilityListUiState?.let {
            horizontalDivider()
            item {
                PokemonAbilityList(
                    abilityListUiState = abilityListUiState,
                    count = pokemon.abilities.count(),
                    onEvent = onEvent,
                )
            }
        }
    }
}

@Composable
private fun Header(
    name: String,
    isPlayingSound: Boolean,
    onEvent: (PokemonDetailScreenEvent) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = name,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.SemiBold,
            ),
        )

        if (isPlayingSound) {
            IconButton(
                onClick = { onEvent(PokemonDetailScreenEvent.OnPauseButtonClick) },
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector = Icons.Outlined.PauseCircleOutline,
                    contentDescription = stringResource(id = R.string.pause_action),
                )
            }
        } else {
            IconButton(
                onClick = { onEvent(PokemonDetailScreenEvent.OnPlayButtonClick) },
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector = Icons.Outlined.PlayCircleOutline,
                    contentDescription = stringResource(id = R.string.play_action),
                )
            }
        }
    }
}


private fun LazyListScope.horizontalDivider() {
    item {
        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .height(2.dp),
        )
    }
}
