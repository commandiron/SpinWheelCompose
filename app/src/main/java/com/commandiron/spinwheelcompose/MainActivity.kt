package com.commandiron.spinwheelcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.commandiron.spin_wheel_compose.DefaultSpinWheel
import com.commandiron.spinwheelcompose.ui.theme.SpinWheelComposeTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpinWheelComposeTheme {
                var isSpinning by remember { mutableStateOf(false)}
                var resultDegree by remember { mutableStateOf(0f)}
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    DefaultSpinWheel(
                        isSpinning = isSpinning,
                        onClick = {
                            if(!isSpinning){
                                resultDegree = Random().nextInt(360).toFloat()
                            }
                            isSpinning = !isSpinning
                        },
                        onFinish = {
                            isSpinning = !isSpinning
                        },
                        resultDegree = resultDegree
                    )
                }
            }
        }
    }
}