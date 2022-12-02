package com.commandiron.spinwheelcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.commandiron.spin_wheel_compose.SpinWheel
import com.commandiron.spin_wheel_compose.SpinWheelDefaults
import com.commandiron.spin_wheel_compose.state.rememberSpinWheelState
import com.commandiron.spinwheelcompose.ui.theme.SpinWheelComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpinWheelComposeTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF3700B3)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val textList by remember {
                        mutableStateOf(
                            listOf("Pie 1", "Pie 2", "Pie 3", "Pie 4", "Pie 5", "Pie 6", "Pie 7", "Pie 8")
                        )
                    }

                    val state = rememberSpinWheelState()
                    val scope = rememberCoroutineScope()

                    SpinWheel(
                        state = state,
                        onClick = { scope.launch { state.animate {pieIndex -> } } }
                    ){ pieIndex ->
                        Text(text = textList[pieIndex])
                    }

//                    val iconList by remember {
//                        mutableStateOf(
//                            listOf(
//                                Icons.Default.Star,
//                                Icons.Default.Star,
//                                Icons.Default.Star,
//                                Icons.Default.Star,
//                            )
//                        )
//                    }
//
//                    repeat(3){
//                        SpinWheel(
//                            state = rememberSpinWheelState(
//                                pieCount = 4,
//                                durationMillis = 20000,
//                                delayMillis = 200,
//                                rotationPerSecond = 2f,
//                                easing = LinearOutSlowInEasing,
//                                startDegree = 90f,
//                                resultDegree = 212f,
//                                autoSpinDelay = 0
//                            ),
//                            dimensions = SpinWheelDefaults.spinWheelDimensions(
//                                spinWheelSize = 180.dp,
//                                frameWidth = 20.dp,
//                                selectorWidth = 10.dp
//                            ),
//                            colors = SpinWheelDefaults.spinWheelColors(
//                                frameColor = Color(0xFF403d39),
//                                dividerColor = Color(0xFFfffcf2),
//                                selectorColor = Color(0xFFdc0073),
//                                pieColors = listOf(
//                                    Color(0xFFdabfff),
//                                    Color(0xFF907ad6),
//                                    Color(0xFF4f518c),
//                                    Color(0xFF2c2a4a)
//                                )
//                            )
//                        ){ pieIndex ->
//                            Icon(
//                                imageVector = iconList[pieIndex],
//                                tint = Color.White,
//                                contentDescription = null
//                            )
//                        }
//                        Spacer(modifier = Modifier.height(32.dp))
//                    }
                }
            }
        }
    }
}