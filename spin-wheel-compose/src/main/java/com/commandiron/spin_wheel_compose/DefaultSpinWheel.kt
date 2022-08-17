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
fun DefaultSpinWheel(
    modifier: Modifier = Modifier,
    size: Dp = 240.dp,
    titleList: List<String> = listOf("Pie 1", "Pie 2", "Pie 3", "Pie 4", "Pie 5", "Pie 6", "Pie 7", "Pie 8"),
    titleTextStyle: TextStyle = MaterialTheme.typography.labelSmall,
    @IntRange(from = 2, to = 8) pieCount: Int = 8,
    frameWidth: Dp = 10.dp,
    frameColor: Color = Color(0xFF941c2f),
    dividerColor: Color = Color.White,
    selectorWidth: Dp = 12.dp,
    selectorColor: Color = Color(0xFFFF0000),
    isSpinning: Boolean = false,
    durationMillis: Int = 12000,
    delayMillis: Int = 0,
    rotationPerSecond: Float = 1f,
    easing: Easing = CubicBezierEasing(0.16f, 1f, 0.3f, 1f),
    startDegree: Float = 0f,
    resultDegree: Float = 0f,
    onClick: () -> Unit = {},
    onFinish: () -> Unit = {}
) {
    AnimatedSpinWheel(
        modifier = modifier,
        size = size,
        titleList = titleList,
        titleTextStyle = titleTextStyle,
        pieCount = pieCount,
        frameWidth = frameWidth,
        frameColor = frameColor,
        dividerColor = dividerColor,
        selectorWidth = selectorWidth,
        selectorColor = selectorColor,
        isSpinning = isSpinning,
        durationMillis = durationMillis,
        delayMillis = delayMillis,
        rotationPerSecond = rotationPerSecond,
        easing = easing,
        startDegree = startDegree,
        resultDegree = resultDegree,
        onClick = onClick,
        onFinish = onFinish
    )
}

