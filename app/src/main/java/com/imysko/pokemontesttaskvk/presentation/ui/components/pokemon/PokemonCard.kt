package com.imysko.pokemontesttaskvk.presentation.ui.components.pokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.imysko.pokemontesttaskvk.R
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonUiModel
import com.imysko.pokemontesttaskvk.presentation.ui.components.base.shimmerEffect
import com.imysko.pokemontesttaskvk.presentation.ui.theme.PokemonTestTaskVKTheme
import com.imysko.pokemontesttaskvk.presentation.utils.preview.PokemonPreviewParameterProvider

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonCard(
    pokemon: PokemonUiModel,
    onCardClick: (Int) -> Unit,
) {
    OutlinedCard(
        elevation = CardDefaults.outlinedCardElevation(
            defaultElevation = 7.dp,
        ),
        onClick = { onCardClick(pokemon.id) },
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                model = pokemon.logoUrl,
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .shimmerEffect(),
                    )
                },
                error = {
                    Box(
                        modifier = Modifier
                            .size(100.dp),
                    ) {
                        Image(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .height(150.dp),
                            painter = painterResource(id = R.drawable.image_gallery),
                            contentDescription = stringResource(id = R.string.image_placeholder),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                        )
                    }
                },
            )

            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = pokemon.name,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                pokemon.types.forEach {
                    Text(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        text = it.name,
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
        }
    }
}

@Composable
fun PokemonCardPlaceholder() {
    OutlinedCard(
        elevation = CardDefaults.outlinedCardElevation(
            defaultElevation = 7.dp,
        ),
        onClick = { },
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shimmerEffect(),
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.35f)
                    .padding(start = 10.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmerEffect(),
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                repeat(2) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .height(24.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .shimmerEffect(),
                    )
                }
            }
        }
    }
}

@Composable
@Preview(name = "Pokemon card")
private fun PokemonCardPreview(
    @PreviewParameter(PokemonPreviewParameterProvider::class) parameter: PokemonUiModel,
) {
    PokemonTestTaskVKTheme {
        PokemonCard(
            pokemon = parameter,
            onCardClick = { },
        )
    }
}

@Composable
@Preview(name = "Pokemon card placeholder")
private fun PokemonCardPlaceholderPreview() {
    PokemonTestTaskVKTheme {
        PokemonCardPlaceholder()
    }
}
