package com.example.sportsteamapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sportsteamapp.data.Player
import com.example.sportsteamapp.ui.components.PlayerImage
import com.example.sportsteamapp.ui.components.ImageSizes

@Composable
fun PlayerListScreen(
    players: List<Player>,
    favorites: Set<Int>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    positionFilter: String?,
    onPositionFilterChange: (String?) -> Unit,
    onPlayerClick: (Player) -> Unit,
    onToggleFavorite: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        // Search and Filter Bar
        SearchAndFilterBar(
            searchQuery = searchQuery,
            onSearchQueryChange = onSearchQueryChange,
            positionFilter = positionFilter,
            onPositionFilterChange = onPositionFilterChange
        )

        // Players Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(players) { player ->
                PlayerCard(
                    player = player,
                    isFavorite = favorites.contains(player.id),
                    onPlayerClick = { onPlayerClick(player) },
                    onToggleFavorite = { onToggleFavorite(player.id) }
                )
            }
        }
    }
}

@Composable
fun PlayerCard(
    player: Player,
    isFavorite: Boolean,
    onPlayerClick: () -> Unit,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onPlayerClick)
    ) {
        Box {
            // Player Image
            PlayerImage(
                imageRes = player.imageRes,
                playerName = player.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ImageSizes.playerCardHeight)
            )

            // Favorite Button
            IconButton(
                onClick = onToggleFavorite,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                    tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }

            // Player Info
            Surface(
                modifier = Modifier.align(Alignment.BottomCenter),
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = player.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${player.position} • #${player.number}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchAndFilterBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    positionFilter: String?,
    onPositionFilterChange: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        // Search TextField
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search players...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            singleLine = true
        )

        // Position Filter Chips
        ScrollablePositionFilter(
            selectedPosition = positionFilter,
            onPositionSelected = onPositionFilterChange
        )
    }
}

@Composable
private fun ScrollablePositionFilter(
    selectedPosition: String?,
    onPositionSelected: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    val positions = listOf("Forward", "Midfielder", "Defender", "Goalkeeper")

    Row(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = selectedPosition == null,
            onClick = { onPositionSelected(null) },
            label = { Text("All") }
        )

        positions.forEach { position ->
            FilterChip(
                selected = position == selectedPosition,
                onClick = { onPositionSelected(if (position == selectedPosition) null else position) },
                label = { Text(position) }
            )
        }
    }
}