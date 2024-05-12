package com.imysko.pokemontesttaskvk.presentation.ui.components.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imysko.pokemontesttaskvk.R
import com.imysko.pokemontesttaskvk.presentation.entities.AbilityUiModel
import com.imysko.pokemontesttaskvk.presentation.entities.PokemonAbilityUiModel
import com.imysko.pokemontesttaskvk.presentation.ui.components.base.shimmerEffect
import com.imysko.pokemontesttaskvk.presentation.ui.theme.PokemonTestTaskVKTheme
import com.imysko.pokemontesttaskvk.presentation.utils.preview.PokemonAbilityPreviewParameterProvider
import com.imysko.pokemontesttaskvk.utils.TestTags

@Composable
fun PokemonAbilityItem(
    slotNumber: Int,
    isHidden: Boolean,
    ability: AbilityUiModel,
) {
    Column(
        modifier = Modifier
            .testTag(TestTags.POKEMON_ABILITY_ITEM)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(
                    modifier = Modifier.testTag(TestTags.POKEMON_ABILITY_NAME),
                    text = ability.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                    )
                )

                if (isHidden) {
                    Text(
                        text = stringResource(id = R.string.ability_hidden),
                        modifier = Modifier
                            .testTag(TestTags.POKEMON_ABILITY_IS_HIDDEN)
                            .background(
                                color = MaterialTheme.colorScheme.outlineVariant,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }

            Text(
                text = stringResource(id = R.string.slot, slotNumber),
                style = MaterialTheme.typography.labelLarge,
            )
        }


        Text(
            text = buildAnnotatedString {
                append(ability.description)
                addStyle(
                    style = ParagraphStyle(
                        textIndent = TextIndent(firstLine = 20.sp),
                    ),
                    start = 0,
                    end = ability.description.length,
                )
            },
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun PokemonAbilityItemPlaceholder() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmerEffect(),
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.15f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmerEffect(),
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .fillMaxWidth(0.9f)
                    .padding(end = 30.dp)
                    .height(15.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmerEffect(),
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .height(15.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmerEffect(),
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(15.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmerEffect(),
            )
        }
    }
}

@Composable
@Preview(name = "Pokemon ability item", group = "Pokemon ability item")
private fun PokemonAbilityItemPreview(
    @PreviewParameter(PokemonAbilityPreviewParameterProvider::class) parameter: PokemonAbilityUiModel,
) {
    PokemonTestTaskVKTheme {
        parameter.ability?.let {
            PokemonAbilityItem(
                slotNumber = parameter.slotNumber,
                isHidden = parameter.isHidden,
                ability = parameter.ability,
            )
        }
    }
}

@Composable
@Preview(name = "Pokemon ability item placeholder")
private fun PokemonAbilityItemPlaceholderPreview() {
    PokemonTestTaskVKTheme {
        PokemonAbilityItemPlaceholder()
    }
}
