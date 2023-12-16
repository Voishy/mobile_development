@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.capstonebangkit.voishy

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.capstonebangkit.voishy.navigation.NavItem
import com.capstonebangkit.voishy.ui.screen.ViewModelFactory
import com.capstonebangkit.voishy.ui.screen.detail.article.ArticleDetailScreen
import com.capstonebangkit.voishy.ui.screen.detail.movie.MovieDetailScreen
import com.capstonebangkit.voishy.ui.screen.home.HomeScreen
import com.capstonebangkit.voishy.ui.screen.materials.MaterialsScreen
import com.capstonebangkit.voishy.ui.screen.voice.ResultScreen
import com.capstonebangkit.voishy.ui.screen.voice.VoiceScreen
import com.capstonebangkit.voishy.ui.screen.voice.VoiceViewModel
import com.capstonebangkit.voishy.ui.theme.VoishyTheme

@Composable
fun VoishyApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
//    val context = LocalContext.current
//    val voiceViewModel: VoiceViewModel = viewModel(
//        factory = ViewModelFactory.getInstanceVoice(context)
//    )

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.ArticleDetail.route && currentRoute != Screen.MovieDetail.route && currentRoute != Screen.ResultScreen.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController = navController)
            }
            composable(Screen.Voice.route) { VoiceScreen(navController) }
            composable(Screen.Materials.route) { MaterialsScreen(navController = navController) }

            // ArticleDetailScreen Composable
            composable(
                route = Screen.ArticleDetail.route,
                arguments = listOf(navArgument("articleTitle") { type = NavType.StringType })
            ) { backStackEntry ->
                val articleTitle = backStackEntry.arguments?.getString("articleTitle") ?: ""
                ArticleDetailScreen(articleTitle = articleTitle)
            }

            // MovieDetailScreen Composable
            composable(
                route = Screen.MovieDetail.route,
                arguments = listOf(navArgument("movieId") { type = NavType.StringType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
                MovieDetailScreen(movieId = movieId)
            }

            // ResultScreen Composable
            composable(route = Screen.ResultScreen.route) {
                // Assuming VoiceViewModel is scoped to the activity
                ResultScreen(navigateBack = {navController.navigate("voice")} )
            }
        }
    }
}


@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navigationItems = listOf(
            NavItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavItem(
                title = stringResource(R.string.menu_voice),
                icon = Icons.Default.RecordVoiceOver,
                screen = Screen.Voice
            ),
            NavItem(
                title = stringResource(R.string.menu_materials),
                icon = Icons.Default.LibraryBooks,
                screen = Screen.Materials
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = false,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    VoishyTheme {
        VoishyApp()
    }
}