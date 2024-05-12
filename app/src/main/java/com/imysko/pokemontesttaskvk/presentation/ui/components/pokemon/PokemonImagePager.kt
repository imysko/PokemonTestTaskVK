package com.imysko.pokemontesttaskvk.presentation.ui.components.pokemon

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.imysko.pokemontesttaskvk.R
import com.imysko.pokemontesttaskvk.presentation.ui.components.base.shimmerEffect
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePager(
    imagesUrl: List<String>,
) {
    val pagerState = rememberPagerState {
        imagesUrl.size
    }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                key = { index -> imagesUrl[index] },
            ) { index ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .height(200.dp),
                        model = imagesUrl[index],
                        alignment = Alignment.Center,
                        contentDescription = stringResource(id = R.string.pokemon_image),
                        contentScale = ContentScale.FillHeight,
                        loading = {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(300.dp)
                                    .shimmerEffect(),
                            )
                        },
                        error = {
                            Image(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(160.dp),
                                painter = painterResource(id = R.drawable.image_gallery),
                                contentDescription = stringResource(id = R.string.image_placeholder),
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                            )
                        },
                    )
                }
            }

            Row(
                modifier = Modifier
                    .matchParentSize()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                    content = {
                        Icon(
                            modifier = Modifier.size(36.dp),
                            imageVector = Icons.Outlined.ChevronLeft,
                            contentDescription = stringResource(id = R.string.previous_image),
                            tint = if (pagerState.currentPage == 0) {
                                MaterialTheme.colorScheme.secondaryContainer
                            } else {
                                MaterialTheme.colorScheme.inversePrimary
                            },
                        )
                    },
                )

                IconButton(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    content = {
                        Icon(
                            modifier = Modifier.size(36.dp),
                            imageVector = Icons.Outlined.ChevronRight,
                            contentDescription = stringResource(id = R.string.next_image),
                            tint = if (pagerState.currentPage == pagerState.pageCount - 1) {
                                MaterialTheme.colorScheme.secondaryContainer
                            } else {
                                MaterialTheme.colorScheme.inversePrimary
                            }
                        )
                    },
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            repeat(pagerState.pageCount) { number ->
                val animatedColor by animateColorAsState(
                    targetValue = if (number == pagerState.currentPage) {
                        Color.Gray
                    } else {
                        Color.LightGray
                    },
                    label = stringResource(id = R.string.animated_color),
                )

                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(animatedColor),
                )
            }
        }
    }
}
