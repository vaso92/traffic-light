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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vasilv.traffic_light.R

@Composable
fun HomeContent(
    modifier: Modifier,
    homeState: HomeState,
    onCarModelChange: (String) -> Unit,
    onStartDriving: (String) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = homeState.carModel,
            onValueChange = onCarModelChange,
            placeholder = { Text(text = stringResource(R.string.home_content_car_model_placeholder)) }
        )

        Spacer(modifier = Modifier.size(8.dp))

        Button(
            onClick = { onStartDriving(homeState.carModel) },
            enabled = homeState.isFormValid
        ) {
            Text(text = stringResource(R.string.home_content_start_driving_button))
        }
    }
}