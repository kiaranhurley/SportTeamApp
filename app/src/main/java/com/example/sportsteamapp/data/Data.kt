package com.example.sportsteamapp.data

import androidx.annotation.DrawableRes

data class Player(
    val id: Int,
    val name: String,
    @DrawableRes val imageRes: Int,
    val position: String,
    val age: Int,
    val number: Int,
    val nationality: String,
    val stats: PlayerStats,
    val bio: String,
    val height: String,
    val weight: String,
    val preferredFoot: String,
    val formerTeams: List<String>
)

data class PlayerStats(
    val appearances: Int,
    val goals: Int,
    val assists: Int,
    val minutesPlayed: Int,
    val passAccuracy: Int,
    val tacklesWon: Int
)

data class Achievement(
    val id: Int,
    val year: Int,
    val title: String,
    val description: String,
    @DrawableRes val imageRes: Int,
    val category: String
)

data class TeamUiState(
    val players: List<Player> = emptyList(),
    val achievements: List<Achievement> = emptyList(),
    val favorites: Set<Int> = emptySet(),
    val selectedPlayer: Player? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val positionFilter: String? = null
)