package com.example.sportsteamapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sportsteamapp.ui.screens.AchievementsScreen
import com.example.sportsteamapp.ui.screens.FavoritesScreen
import com.example.sportsteamapp.ui.screens.PlayerDetailScreen
import com.example.sportsteamapp.ui.screens.PlayerListScreen
import com.example.sportsteamapp.viewmodel.TeamViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.dp

sealed class Screen(val route: String) {
    data object PlayerList : Screen("players")
    data object PlayerDetail : Screen("player/{playerId}") {
        fun createRoute(playerId: Int) = "player/$playerId"
    }
    data object Favorites : Screen("favorites")
    data object Achievements : Screen("achievements")

    companion object {
        fun fromRoute(route: String?): Screen {
            return when (route?.substringBefore("/")) {
                "players" -> PlayerList
                "player" -> PlayerDetail
                "favorites" -> Favorites
                "achievements" -> Achievements
                null -> PlayerList
                else -> PlayerList
            }
        }
    }
}

private const val ANIMATION_DURATION = 300

@Composable
fun TeamNavigation(
    navController: NavHostController,
    viewModel: TeamViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    // Handle error states
    uiState.error?.let { error ->
        LaunchedEffect(error) {
            viewModel.refreshData()
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.PlayerList.route,
        modifier = modifier,
        enterTransition = { defaultEnterTransition(initialState, targetState) },
        exitTransition = { defaultExitTransition(initialState, targetState) },
        popEnterTransition = { defaultPopEnterTransition() },
        popExitTransition = { defaultPopExitTransition() }
    ) {
        // Player List Screen
        composable(
            route = Screen.PlayerList.route
        ) {
            PlayerListScreen(
                players = uiState.players,
                favorites = uiState.favorites,
                searchQuery = uiState.searchQuery,
                onSearchQueryChange = viewModel::setSearchQuery,
                positionFilter = uiState.positionFilter,
                onPositionFilterChange = viewModel::setPositionFilter,
                onPlayerClick = { player ->
                    navController.navigate(Screen.PlayerDetail.createRoute(player.id))
                },
                onToggleFavorite = viewModel::toggleFavorite
            )
        }

        // Player Detail Screen
        composable(
            route = Screen.PlayerDetail.route,
            arguments = listOf(
                navArgument("playerId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val playerId = backStackEntry.arguments?.getInt("playerId")
            val player = uiState.players.find { it.id == playerId }

            if (uiState.isLoading) {
                LoadingScreen()
            } else if (player != null) {
                PlayerDetailScreen(
                    player = player,
                    isFavorite = uiState.favorites.contains(player.id),
                    onBackClick = { navController.popBackStack() },
                    onToggleFavorite = { viewModel.toggleFavorite(player.id) }
                )
            } else {
                ErrorScreen(
                    message = "Player not found",
                    onRetry = { navController.popBackStack() }
                )
            }
        }

        // Favorites Screen
        composable(
            route = Screen.Favorites.route
        ) {
            FavoritesScreen(
                players = uiState.players.filter { uiState.favorites.contains(it.id) },
                onPlayerClick = { player ->
                    navController.navigate(Screen.PlayerDetail.createRoute(player.id))
                },
                onToggleFavorite = viewModel::toggleFavorite
            )
        }

        // Achievements Screen
        composable(
            route = Screen.Achievements.route
        ) {
            AchievementsScreen(
                achievements = uiState.achievements
            )
        }
    }
}

// Helper function to handle default enter transition
private fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultEnterTransition(
    initial: NavBackStackEntry,
    target: NavBackStackEntry
): EnterTransition {
    val initialRoute = Screen.fromRoute(initial.destination.route)
    val targetRoute = Screen.fromRoute(target.destination.route)

    return when {
        initialRoute is Screen.PlayerList && targetRoute is Screen.PlayerDetail -> {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(ANIMATION_DURATION)
            )
        }
        else -> EnterTransition.None
    }
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultExitTransition(
    initial: NavBackStackEntry,
    target: NavBackStackEntry
): ExitTransition {
    val initialRoute = Screen.fromRoute(initial.destination.route)
    val targetRoute = Screen.fromRoute(target.destination.route)

    return when {
        initialRoute is Screen.PlayerList && targetRoute is Screen.PlayerDetail -> {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(ANIMATION_DURATION)
            )
        }
        else -> ExitTransition.None
    }
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultPopEnterTransition(): EnterTransition {
    return slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(ANIMATION_DURATION)
    )
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultPopExitTransition(): ExitTransition {
    return slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(ANIMATION_DURATION)
    )
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorScreen(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = message)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetry) {
                Text("Try Again")
            }
        }
    }
}