package com.commandiron.spinwheelcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.commandiron.spin_wheel_compose.AnimatedSpinWheel
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
                    AnimatedSpinWheel(
                        state = state,
                        onClick = {
                            scope.launch {
                                state.spin{
                                    println(it)
                                }
                            }
                        }
                    ){ pieIndex ->
                        Text(text = textList[pieIndex])
                    }

//                    DefaultSpinWheel(isSpinning = true){ pieIndex ->
//                        Text(text = textList[pieIndex])
//                    }

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
//                    var isSpinning by remember { mutableStateOf(false)}
//                    repeat(3){
//                        DefaultSpinWheel(
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
//                            ),
//                            animationAttr = SpinWheelDefaults.spinWheelAnimationAttr(
//                                pieCount = 4,
//                                durationMillis = 4000,
//                                delayMillis = 200,
//                                rotationPerSecond = 2f,
//                                easing = LinearOutSlowInEasing,
//                                startDegree = 90f,
//                                autoResetDelay = 500
//                            ),
//                            isSpinning = isSpinning,
//                            onClick = { isSpinning = !isSpinning },
//                            onFinish = { isSpinning = false }
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