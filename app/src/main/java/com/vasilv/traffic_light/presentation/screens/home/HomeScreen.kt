package com.vasilv.traffic_light.presentation.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val state by homeViewModel.state.collectAsStateWithLifecycle()

    HomeContent(
        modifier = Modifier.fillMaxSize(),
        homeState = state,
        onCarModelChange = { homeViewModel.onCarModelChange(it) },
        onStartDriving = { homeViewModel.onStartDriving() }
    )
}