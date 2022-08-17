package com.commandiron.spin_wheel_compose

import androidx.annotation.IntRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
internal fun SpinWheelPies(
    modifier: Modifier = Modifier,
    spinSize: Dp,
    @IntRange(from = 2, to = 8) pieCount: Int,
    rotationDegree: Float,
    onClick: () -> Unit,
    spinWheelContent: @Composable BoxScope.() -> Unit
) {
    val pieAngle = 360f / pieCount
    val startAngleOffset = 180
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier
                .size(spinSize)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClick
                )
        ){
            val canvasWidth = size.width
            val canvasHeight = size.height
            for(i in 0 until pieCount){
                val startAngle = pieAngle * i + rotationDegree + startAngleOffset
                val nextColor = nextColor(i)
                drawArc(
                    color = nextColor,
                    startAngle = startAngle,
                    sweepAngle = pieAngle,
                    useCenter = true,
                    size = Size(canvasWidth, canvasHeight)
                )
            }
        }
        spinWheelContent()
    }
}

private fun nextColor(i: Int): Color{
    return when(i){
        0 -> Color(0xFFef476f)
        1 -> Color(0xFFf78c6b)
        2 -> Color(0xFFffd166)
        3 -> Color(0xFF83d483)
        4 -> Color(0xFF06d6a0)
        5 -> Color(0xFF0cb0a9)
        6 -> Color(0xFF118ab2)
        7 -> Color(0xFF073b4c)
        else -> Color(0xFFef476f)
    }
}