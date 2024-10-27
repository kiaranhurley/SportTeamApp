package com.example.sportsteamapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sportsteamapp.data.Achievement
import com.example.sportsteamapp.ui.components.ImageSizes
import com.example.sportsteamapp.ui.components.TrophyImage

@Composable
fun AchievementsScreen(
    achievements: List<Achievement>,
    modifier: Modifier = Modifier
) {
    val groupedAchievements = achievements.groupBy { it.category }
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Team Achievements",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(16.dp)
            )
        }

        CategoryFilter(
            categories = groupedAchievements.keys.toList(),
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val filteredAchievements = if (selectedCategory != null) {
                groupedAchievements[selectedCategory] ?: emptyList()
            } else {
                achievements
            }

            items(filteredAchievements) { achievement ->
                AchievementCard(achievement = achievement)
            }
        }
    }
}

@Composable
private fun CategoryFilter(
    categories: List<String>,
    selectedCategory: String?,
    onCategorySelected: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 4.dp,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SuggestionChip(
                onClick = { onCategorySelected(null) },
                label = { Text("All") },
                icon = {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Clear filter",
                        modifier = Modifier.size(18.dp)
                    )
                }
            )

            categories.forEach { category ->
                SuggestionChip(
                    onClick = { onCategorySelected(if (category == selectedCategory) null else category) },
                    label = { Text(category) },
                    icon = {
                        Icon(
                            if (category == selectedCategory) Icons.Default.Check
                            else Icons.Default.Star,  // Changed from RadioButtonUnchecked to Star
                            contentDescription = category,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun AchievementCard(
    achievement: Achievement,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TrophyImage(
                imageRes = achievement.imageRes,
                trophyName = achievement.title,
                modifier = Modifier.size(ImageSizes.trophyMedium)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = achievement.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = achievement.year.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = achievement.description,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}