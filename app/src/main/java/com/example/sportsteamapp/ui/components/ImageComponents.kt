package com.example.sportsteamapp.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun PlayerImage(
    imageRes: Int,
    playerName: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageRes)
            .crossfade(true)
            .build(),
        contentDescription = "Photo of $playerName",
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(3f/4f), // 3:4 aspect ratio for player photos
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TrophyImage(
    imageRes: Int,
    trophyName: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageRes)
            .crossfade(true)
            .build(),
        contentDescription = trophyName,
        modifier = modifier
            .size(200.dp), // Fixed size for trophies
        contentScale = ContentScale.Fit
    )
}

// Predefined sizes for different use cases
object ImageSizes {
    // Player image sizes
    val playerCardHeight = 250.dp
    val playerDetailHeight = 400.dp

    // Trophy image sizes
    val trophyMedium = 150.dp
}