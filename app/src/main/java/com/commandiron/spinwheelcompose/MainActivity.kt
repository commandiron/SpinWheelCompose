package com.commandiron.spinwheelcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.commandiron.spin_wheel_compose.DefaultSpinWheel
import com.commandiron.spinwheelcompose.ui.theme.SpinWheelComposeTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpinWheelComposeTheme {
                var isSpinning by remember { mutableStateOf(false) }
                var resultDegree by remember { mutableStateOf(0f) }
                LaunchedEffect(key1 = isSpinning){
                    if(!isSpinning){
                        resultDegree = Random().nextInt(360).toFloat()
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF3700B3)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    DefaultSpinWheel(
                        isSpinning = isSpinning,
                        resultDegree = resultDegree,
                        onClick = { isSpinning = !isSpinning },
                        onFinish = { isSpinning = false }
                    )
                }
            }
        }
    }
}