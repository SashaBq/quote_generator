package com.example.quote_generator.presentation.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quote_generator.presentation.ui.QuotesListScreen
import com.example.quote_generator.presentation.ui.QuotesScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "quote_main"
    ) {
        composable("quote_main") {
            QuotesScreen(
                onNavigateToList = { navController.navigate("quote_list") }
            )
        }
        composable("quote_list") {
            QuotesListScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}