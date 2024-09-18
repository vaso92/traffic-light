package com.vasilv.traffic_light.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHost
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.vasilv.traffic_light.presentation.screens.home.HomeRoute
import com.vasilv.traffic_light.presentation.screens.home.HomeScreen
import com.vasilv.traffic_light.presentation.screens.home.HomeViewModel
import com.vasilv.traffic_light.presentation.screens.traffic_light.TrafficLightRoute
import com.vasilv.traffic_light.presentation.screens.traffic_light.TrafficLightScreen
import com.vasilv.traffic_light.presentation.ui.theme.TrafficlightTheme

class MainActivity : ComponentActivity() {
    private val homeViewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            TrafficlightTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = HomeRoute
                    ) {
                        composable<HomeRoute> {
                            HomeScreen(homeViewModel = homeViewModel,
                                navigateToTrafficLight = { carModel ->
                                    navController.navigate(
                                        TrafficLightRoute(carModel)
                                    )
                                })
                        }

                        composable<TrafficLightRoute> {
                            val arguments = it.toRoute<TrafficLightRoute>()
                            TrafficLightScreen(arguments)
                        }
                    }
                }
            }
        }
    }
}
