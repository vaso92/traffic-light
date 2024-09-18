package com.vasilv.traffic_light.presentation.screens.traffic_light

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data class TrafficLightRoute(val carModel: String)

@Composable
fun TrafficLightScreen(trafficLightRoute: TrafficLightRoute) {
    TrafficLightContent(
        modifier = Modifier.fillMaxSize(),
        carModel = trafficLightRoute.carModel
    )
}