package com.commandiron.spin_wheel_compose

import androidx.annotation.IntRange
import androidx.compose.animation.core.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SpinWheel(
    modifier: Modifier = Modifier,
    spinSize: Dp = 240.dp,
    @IntRange(from = 2, to = 8) pieCount: Int = 8,
    titleList: List<String> = listOf("Pie 1", "Pie 2", "Pie 3", "Pie 4"),
    titleTextStyle: TextStyle = MaterialTheme.typography.labelSmall,
    borderWidth: Dp = 10.dp,
    borderColor: Color = Color(0xFF941c2f),
    dividerColor: Color = Color.White,
    durationMillis: Int = 5000,
    delayMillis: Int = 0,
    rotationPerSecond: Int = 5,
    startDegree: Float = 0f,
    resultDegree: Float = 0f,
    easing: Easing = CubicBezierEasing(0.16f, 1f, 0.3f, 1f),
    isSpinning: Boolean = false,
    onClickEnabled: Boolean = true,
    onClick: () -> Unit = {},
    onFinish: () -> Unit = {}
) {
    AnimatedSpinWheel(
        modifier = modifier,
        spinSize = spinSize,
        pieCount = pieCount,
        titleList = titleList,
        titleTextStyle = titleTextStyle,
        borderWidth = borderWidth,
        borderColor = borderColor,
        dividerColor = dividerColor,
        durationMillis = durationMillis,
        delayMillis = delayMillis,
        rotationPerSecond = rotationPerSecond,
        startDegree = startDegree,
        resultDegree = resultDegree,
        easing = easing,
        isSpinning = isSpinning,
        onClickEnabled = onClickEnabled,
        onClick = onClick,
        onFinish = onFinish
    )
}

