package com.imysko.pokemontesttaskvk.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.imysko.pokemontesttaskvk.domain.entities.Pokemon
import com.imysko.pokemontesttaskvk.presentation.ui.components.base.shimmerEffect

@Composable
fun ColumnScope.PokemonCard(
    pokemon: Pokemon,
    onCardClick: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .weight(0.4f),
        onClick = { onCardClick(pokemon.id) },
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = pokemon.logoUrl,
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

        Text(text = pokemon.name)
        Text(text = pokemon.height.toString())
    }
}