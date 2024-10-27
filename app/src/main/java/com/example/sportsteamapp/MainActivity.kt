package com.example.sportsteamapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sportsteamapp.navigation.Screen
import com.example.sportsteamapp.navigation.TeamNavigation
import com.example.sportsteamapp.ui.theme.SportsTeamAppTheme
import com.example.sportsteamapp.viewmodel.TeamViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportsTeamAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TeamApp()
                }
            }
        }
    }
}

@Composable
fun TeamApp() {
    val navController = rememberNavController()
    val viewModel: TeamViewModel = viewModel()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Group, "Players") },
                    label = { Text("Players") },
                    selected = currentRoute == Screen.PlayerList.route,
                    onClick = {
                        navController.navigate(Screen.PlayerList.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Star, "Favorites") },
                    label = { Text("Favorites") },
                    selected = currentRoute == Screen.Favorites.route,
                    onClick = {
                        navController.navigate(Screen.Favorites.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.EmojiEvents, "Achievements") },
                    label = { Text("Achievements") },
                    selected = currentRoute == Screen.Achievements.route,
                    onClick = {
                        navController.navigate(Screen.Achievements.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            TeamNavigation(navController, viewModel)
        }
    }
}