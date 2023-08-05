package com.fikrisandi.todoapps

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fikrisandi.theme.TodoTheme
import com.fikrisandi.todoapps.provider.AppNavigationProvider
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency


@Composable
fun MainRootApp(onFinishApps: () -> Unit) {
    val navController = rememberNavController()
    val currentNavigationStackEntryState by navController.currentBackStackEntryAsState()
    val destination =
        currentNavigationStackEntryState?.destination?.route ?: RootNavGraph.startRoute.route

    if (destination == RootNavGraph.startRoute.route) {
        BackHandler { onFinishApps() }
    }

    TodoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DestinationsNavHost(
                navGraph = RootNavGraph,
                navController = navController,
                dependenciesContainerBuilder = {
                    dependency(AppNavigationProvider(navController))
                })
        }
    }
}