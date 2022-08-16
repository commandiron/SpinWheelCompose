package com.commandiron.spin_wheel_compose

import androidx.annotation.IntRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SpinWheelFrame(
    modifier: Modifier = Modifier,
    spinSize: Dp,
    @IntRange(from = 2, to = 8) pieCount: Int,
    rotationDegree: Float,
    borderWidth: Dp,
    borderColor: Color,
    dividerColor: Color,
    onClickEnabled: Boolean,
    onClick: () -> Unit,
    spinWheel: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(spinSize)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    enabled = onClickEnabled,
                    onClick = onClick
                )
        ){
            val canvasWidth = size.width
            val canvasHeight = size.height
            rotate(rotationDegree){
                drawArc(
                    color = borderColor,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = true,
                    style = Stroke(borderWidth.toPx()),
                    size = Size(canvasWidth, canvasHeight)
                )
            }
            //Bound Separator Dot
            val pieAngle = 360f / pieCount
            val boundSeparatorRadius = borderWidth.toPx() / 4
            val spinRadius = canvasWidth / 2
            for(i in 0 until pieCount){
                val angle = pieAngle * i + rotationDegree
                val offsetX = -(spinRadius * sin(Math.toRadians(angle.toDouble()))).toFloat() + spinRadius
                val offsetY = (spinRadius * cos(Math.toRadians(angle.toDouble()))).toFloat() + spinRadius
                drawCircle(
                    color = dividerColor,
                    radius = boundSeparatorRadius,
                    center = Offset(x = offsetX, y = offsetY)
                )
            }
            //Middle Separator Dot
            val middleSeparatorRadius = borderWidth.toPx() / 8
            for(i in 0 until pieCount){
                val angle = pieAngle * i + pieAngle / 2 + rotationDegree
                val offsetX = -(spinRadius * sin(Math.toRadians(angle.toDouble()))).toFloat() + spinRadius
                val offsetY = (spinRadius * cos(Math.toRadians(angle.toDouble()))).toFloat() + spinRadius
                drawCircle(
                    color = dividerColor,
                    radius = middleSeparatorRadius,
                    center = Offset(x = offsetX, y = offsetY)
                )
            }
        }
        spinWheel()
    }
}