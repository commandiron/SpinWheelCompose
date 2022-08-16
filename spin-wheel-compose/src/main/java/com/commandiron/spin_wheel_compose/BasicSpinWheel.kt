package com.commandiron.spin_wheel_compose

import androidx.annotation.IntRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BasicSpinWheel(
    modifier: Modifier = Modifier,
    spinSize: Dp = 240.dp,
    @IntRange(from = 2, to = 8) pieCount: Int = 8,
    titleList: List<String> = listOf("Pie 1", "Pie 2", "Pie 3", "Pie 4"),
    startDegree: Float = 0f,
    resultDegree: Float = 0f,
    isSpinning: Boolean = false,
    onClickEnabled: Boolean = true,
    onClick: () -> Unit = {},
    onFinish: () -> Unit = {}
) {
    SpinWheel(
        modifier = modifier,
        spinSize = spinSize,
        pieCount = pieCount,
        titleList = titleList,
        startDegree = startDegree,
        resultDegree = resultDegree,
        isSpinning = isSpinning,
        onClickEnabled = onClickEnabled,
        onClick = onClick,
        onFinish = onFinish
    )
}