package com.vasilv.traffic_light.presentation.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navigateToTrafficLight: (String) -> Unit
) {
    val state by homeViewModel.state.collectAsStateWithLifecycle()

    HomeContent(
        modifier = Modifier.fillMaxSize(),
        homeState = state,
        onCarModelChange = { homeViewModel.onCarModelChange(it) },
        onStartDriving = navigateToTrafficLight
    )
}