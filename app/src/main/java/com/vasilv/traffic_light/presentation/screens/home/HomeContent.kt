package com.vasilv.traffic_light.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeContent(
    modifier: Modifier,
    homeState: HomeState,
    onCarModelChange: (String) -> Unit,
    onStartDriving: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = homeState.carModel,
            onValueChange = onCarModelChange,
            placeholder = { Text(text = "Car model") }
        )

        Spacer(modifier = Modifier.size(8.dp))

        Button(
            onClick = onStartDriving,
            enabled = homeState.isFormValid
        ) {
            Text(text = "Start Driving")
        }
    }
}