package com.mgarzondev.paymentspt.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mgarzondev.paymentspt.ui.screens.authorizationScreen.AuthScreen
import com.mgarzondev.paymentspt.ui.screens.searchTransactionScreen.SearchScreen
import com.mgarzondev.paymentspt.ui.screens.transactionListScreen.TransactionScreen

enum class Destination(val route: String, val label: String, val icon: ImageVector, val contentDescription: String) {
    AUTH("auth", "Autorizar", Icons.Default.Home, "Go to home new transaction"),
    SEARCH("search", "Buscar", Icons.Default.Search, "Go to search transaction"),
    LIST("transactions", "Listado", Icons.Default.List, "Go to list of transactions")
}

@Composable
fun NavigationBottonBar(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = Destination.AUTH
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(destination.route)
                            selectedDestination = index
                        },
                        icon = { Icon(destination.icon, contentDescription = destination.contentDescription) },
                        label = { Text(destination.label) }
                    )
                }
            }
        }
    ) { contentPadding ->
        AppNavHost(navController = navController, startDestination = startDestination, modifier = Modifier.padding(contentPadding))
    }
}

@Composable
fun AppNavHost(navController: NavHostController, startDestination: Destination, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = startDestination.route, modifier = modifier) {
        composable(Destination.AUTH.route) { AuthScreen() }
        composable(Destination.SEARCH.route) { SearchScreen() }
        composable(Destination.LIST.route) { TransactionScreen() }
    }
}