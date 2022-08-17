package com.commandiron.spinwheelcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
                val textList by remember { mutableStateOf(listOf("Pie 1", "Pie 2", "Pie 3", "Pie 4", "Pie 5", "Pie 6", "Pie 7", "Pie 8"))}
                val iconList by remember { mutableStateOf(listOf(
                    Icons.Default.Settings,
                    Icons.Default.List,
                    Icons.Default.Create,
                    Icons.Default.Add
                )) }
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
                        pieCount = 4,
                        isSpinning = isSpinning,
                        resultDegree = resultDegree,
                        onClick = { isSpinning = !isSpinning },
                        onFinish = { isSpinning = false }
                    ){ pieIndex ->
                        Icon(imageVector = iconList[pieIndex], contentDescription = null)
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    DefaultSpinWheel(
                        pieCount = 8,
                        isSpinning = isSpinning,
                        resultDegree = resultDegree,
                        onClick = { isSpinning = !isSpinning },
                        onFinish = { isSpinning = false }
                    ){ pieIndex ->
                        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
                    }
//                    Spacer(modifier = Modifier.height(32.dp))
//                    DefaultSpinWheel(
//                        isSpinning = isSpinning,
//                        resultDegree = resultDegree,
//                        onClick = { isSpinning = !isSpinning },
//                        onFinish = { isSpinning = false }
//                    ){ pieIndex ->
//                        Text(text = textList[pieIndex])
//                    }
                }
            }
        }
    }
}