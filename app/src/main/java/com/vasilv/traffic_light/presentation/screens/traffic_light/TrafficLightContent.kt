package com.vasilv.traffic_light.presentation.screens.traffic_light

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vasilv.traffic_light.presentation.ui.theme.TrafficlightTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.time.Duration.Companion.seconds

val TRAFFIC_LIGHT_SPACING = 10.dp

const val LIGHT_TURNED_OFF_ALPHA = 0.3f

val GREEN = Color(0xff2dc937)
val GREEN_TURNED_OFF = GREEN.copy(alpha = LIGHT_TURNED_OFF_ALPHA)

val ORANGE = Color(0xffdb7b2b)
val ORANGE_TURNED_OFF = ORANGE.copy(alpha = LIGHT_TURNED_OFF_ALPHA)

val RED = Color(0xffcc3232)
val RED_TURNED_OFF = RED.copy(alpha = LIGHT_TURNED_OFF_ALPHA)

@Composable
fun TrafficLightContent(
    modifier: Modifier,
    carModel: String,
    lightCircleSize: Dp = 48.dp
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.size(30.dp))
        Text(
            text = carModel,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.size(40.dp))

        val redLight = remember { Animatable(RED_TURNED_OFF) }
        val orangeLight = remember { Animatable(ORANGE_TURNED_OFF) }
        val greenLight = remember { Animatable(GREEN_TURNED_OFF) }

        LaunchedEffect(Unit) {
            while (isActive) {
                redLight.animateTo(RED)

                delay(4.seconds)
                redLight.animateTo(RED_TURNED_OFF)
                orangeLight.animateTo(ORANGE)

                delay(1.seconds)
                orangeLight.animateTo(ORANGE_TURNED_OFF)
                greenLight.animateTo(GREEN)

                delay(4.seconds)
                greenLight.animateTo(GREEN_TURNED_OFF)
                orangeLight.animateTo(ORANGE)

                delay(1.seconds)
                orangeLight.animateTo(ORANGE_TURNED_OFF)
            }
        }

        Column(
            modifier = Modifier
                .background(Color.Black)
                .padding(TRAFFIC_LIGHT_SPACING)
                .semantics(mergeDescendants = true) {
                    contentDescription = when {
                        redLight.value == RED -> "Red light"
                        orangeLight.value == ORANGE -> "Orange light"
                        greenLight.value == GREEN -> "Green light"
                        else -> ""
                    }
                },
            verticalArrangement = Arrangement.spacedBy(TRAFFIC_LIGHT_SPACING)
        ) {
            Box(
                modifier = Modifier
                    .size(lightCircleSize)
                    .clip(CircleShape)
                    .background(redLight.value)
            )
            Box(
                modifier = Modifier
                    .size(lightCircleSize)
                    .clip(CircleShape)
                    .background(orangeLight.value)
            )
            Box(
                modifier = Modifier
                    .size(lightCircleSize)
                    .clip(CircleShape)
                    .background(greenLight.value)
            )
        }
    }
}

@Preview
@Composable
fun TrafficLightContentPreview() {
    TrafficlightTheme {
        Surface {
            TrafficLightContent(modifier = Modifier.fillMaxSize(), carModel = "Toyota")
        }
    }
}