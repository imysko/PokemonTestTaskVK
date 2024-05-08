package com.imysko.pokemontesttaskvk.presentation.ui.screens.pokemonDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.SubcomposeAsyncImage
import com.imysko.pokemontesttaskvk.domain.entities.navigation.NavDestinations
import com.imysko.pokemontesttaskvk.presentation.ui.components.base.shimmerEffect

@Composable
fun PokemonDetailScreen(
    arguments: NavDestinations.PokemonDetail,
    viewModel: PokemonDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(LocalLifecycleOwner.current) {
        viewModel.getPokemon(arguments.id)
    }

    LaunchedEffect(uiState) {
        if (uiState is PokemonDetailUiState.ShowPokemonDetail) {
            viewModel.mediaPlayer.apply {
                setDataSource(
                    (uiState as PokemonDetailUiState.ShowPokemonDetail).pokemon.crySoundUrl
                )
                prepareAsync()
            }
        }
    }

    PokemonDetailScreen(
        uiState = uiState,
        onPlayButtonClick = {
            viewModel.mediaPlayer.apply {
                if (isPlaying) {
                    stop()
                }
                start()
            }
        },
    )
}

@Composable
fun PokemonDetailScreen(
    uiState: PokemonDetailUiState,
    onPlayButtonClick: () -> Unit,
) {
    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                when (uiState) {
                    is PokemonDetailUiState.ShowPokemonDetail -> {
                        SubcomposeAsyncImage(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            model = uiState.pokemon.logoUrl,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            loading = {
                                Box(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .shimmerEffect(),
                                )
                            },
                            error = {
                                // TODO
                            },
                        )

                        Text(text = uiState.pokemon.name)

                        IconButton(
                            onClick = onPlayButtonClick,
                            content = {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    imageVector = Icons.Rounded.PlayCircleOutline,
                                    contentDescription = "",
                                )
                            },
                        )
                    }

                    else -> {}
                }
            }
        },
    )
}
