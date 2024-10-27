package com.example.sportsteamapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsteamapp.data.Player
import com.example.sportsteamapp.data.TeamRepository
import com.example.sportsteamapp.data.TeamUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class TeamViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TeamUiState())
    val uiState: StateFlow<TeamUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true,
                    error = null // Clear any previous errors
                )
            }

            try {
                val players = withContext(Dispatchers.IO) {
                    TeamRepository.players
                }
                val achievements = withContext(Dispatchers.IO) {
                    TeamRepository.achievements
                }

                _uiState.update { currentState ->
                    currentState.copy(
                        players = players,
                        achievements = achievements,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        error = when (e) {
                            is IOException -> "Network error. Please check your connection."
                            is SecurityException -> "Permission denied. Please check app permissions."
                            else -> "An unexpected error occurred: ${e.message}"
                        }
                    )
                }
            }
        }
    }

    fun selectPlayer(player: Player) {
        viewModelScope.launch {
            _uiState.update { it.copy(selectedPlayer = player) }
        }
    }

    fun clearSelectedPlayer() {
        viewModelScope.launch {
            _uiState.update { it.copy(selectedPlayer = null) }
        }
    }

    fun toggleFavorite(playerId: Int) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                val currentFavorites = currentState.favorites
                val newFavorites = if (currentFavorites.contains(playerId)) {
                    currentFavorites - playerId
                } else {
                    currentFavorites + playerId
                }
                currentState.copy(favorites = newFavorites)
            }
        }
    }

    fun setSearchQuery(query: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(searchQuery = query) }
            filterPlayers()
        }
    }

    fun setPositionFilter(position: String?) {
        viewModelScope.launch {
            _uiState.update { it.copy(positionFilter = position) }
            filterPlayers()
        }
    }

    private fun filterPlayers() {
        viewModelScope.launch {
            val currentState = _uiState.value
            val filteredPlayers = withContext(Dispatchers.Default) {
                TeamRepository.players.filter { player ->
                    val matchesSearch = player.name.contains(currentState.searchQuery, ignoreCase = true) ||
                            player.nationality.contains(currentState.searchQuery, ignoreCase = true)
                    val matchesPosition = currentState.positionFilter == null ||
                            player.position == currentState.positionFilter

                    matchesSearch && matchesPosition
                }
            }
            _uiState.update { it.copy(players = filteredPlayers) }
        }
    }

    fun refreshData() {
        loadData()
    }

    companion object {
        private const val TAG = "TeamViewModel"
    }
}