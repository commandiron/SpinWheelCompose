package com.commandiron.spin_wheel_compose

import androidx.annotation.IntRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp

@Composable
internal fun SpinWheelSelector(
    modifier: Modifier = Modifier,
    frameSize: Dp,
    @IntRange(from = 2, to = 8) pieCount: Int,
    selectorWidth: Dp,
    selectorColor: Color,
    rotationDegree: Float,
    spinWheelFrame: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        spinWheelFrame()
        Canvas(Modifier.size(frameSize)){
            val canvasHeight = size.height
            val rect = Rect(center + Offset(x = 0f, y =  - canvasHeight / 2), selectorWidth.toPx())
            val trianglePath = Path().apply {
                moveTo(rect.bottomCenter.x, rect.bottomCenter.y)
                lineTo(rect.topRight.x, rect.topRight.y)
                lineTo(rect.topLeft.x, rect.topLeft.y)
                close()
            }
            rotate(
                degrees = calculateSelectorRotation(rotationDegree, pieCount),
                pivot = center + Offset(x = 0f, y = -canvasHeight / 2)
            ){
                drawIntoCanvas { canvas ->
                    canvas.drawOutline(
                        outline = Outline.Generic(trianglePath),
                        paint = Paint().apply {
                            color = selectorColor
                            pathEffect = PathEffect.cornerPathEffect(rect.maxDimension / 5)
                        }
                    )
                }
            }
        }
    }
}

private fun calculateSelectorRotation(rotationDegree: Float, pieCount: Int): Float {
    val pieAngle = 360f / pieCount
    val pieCountRemind = pieCount % 4
    val pieAngleOffset = pieAngle * pieCountRemind / 4

    val rotationDegreeForSelector =  rotationDegree - pieAngleOffset

    val rotationDegreeForSelectorPercent = (rotationDegreeForSelector / 360) % 1

    val actualRotationDegreeForSelectorPercent = (rotationDegreeForSelectorPercent * pieCount) % 1

    val selectorBumpSensitivity = 0.001f //For shorten code
    val selectorReleaseSensitivity = 0.002f //For shorten code

    return when {
        actualRotationDegreeForSelectorPercent > 0.001f && actualRotationDegreeForSelectorPercent < 0.002f -> -5f
        actualRotationDegreeForSelectorPercent > 0.002f && actualRotationDegreeForSelectorPercent < 0.003f -> -10f
        actualRotationDegreeForSelectorPercent > 0.003f && actualRotationDegreeForSelectorPercent < 0.004f -> -15f
        actualRotationDegreeForSelectorPercent > 0.004f && actualRotationDegreeForSelectorPercent < 0.005f -> -20f
        actualRotationDegreeForSelectorPercent > 0.005f && actualRotationDegreeForSelectorPercent < 0.006f -> -25f
        actualRotationDegreeForSelectorPercent > 0.006f && actualRotationDegreeForSelectorPercent < 0.007f -> -30f
        actualRotationDegreeForSelectorPercent > 0.007f && actualRotationDegreeForSelectorPercent < 0.008f -> -35f
        actualRotationDegreeForSelectorPercent > 0.008f && actualRotationDegreeForSelectorPercent < 0.009f -> -40f
        actualRotationDegreeForSelectorPercent > 0.009f && actualRotationDegreeForSelectorPercent < 0.010f -> -45f

        actualRotationDegreeForSelectorPercent > 0.998f && actualRotationDegreeForSelectorPercent < 1f -> -45f
        actualRotationDegreeForSelectorPercent > 0.996f && actualRotationDegreeForSelectorPercent < 0.998f -> -43f
        actualRotationDegreeForSelectorPercent > 0.994f && actualRotationDegreeForSelectorPercent < 0.996f -> -41f
        actualRotationDegreeForSelectorPercent > 0.992f && actualRotationDegreeForSelectorPercent < 0.994f -> -39f
        actualRotationDegreeForSelectorPercent > 0.990f && actualRotationDegreeForSelectorPercent < 0.992f -> -37f
        actualRotationDegreeForSelectorPercent > 0.988f && actualRotationDegreeForSelectorPercent < 0.990f -> -35f
        actualRotationDegreeForSelectorPercent > 0.986f && actualRotationDegreeForSelectorPercent < 0.988f -> -33f
        actualRotationDegreeForSelectorPercent > 0.984f && actualRotationDegreeForSelectorPercent < 0.986f -> -31f
        actualRotationDegreeForSelectorPercent > 0.982f && actualRotationDegreeForSelectorPercent < 0.984f -> -29f
        actualRotationDegreeForSelectorPercent > 0.980f && actualRotationDegreeForSelectorPercent < 0.982f -> -27f
        actualRotationDegreeForSelectorPercent > 0.978f && actualRotationDegreeForSelectorPercent < 0.980f -> -25f
        actualRotationDegreeForSelectorPercent > 0.976f && actualRotationDegreeForSelectorPercent < 0.978f -> -23f
        actualRotationDegreeForSelectorPercent > 0.974f && actualRotationDegreeForSelectorPercent < 0.976f -> -21f
        actualRotationDegreeForSelectorPercent > 0.972f && actualRotationDegreeForSelectorPercent < 0.974f -> -19f
        actualRotationDegreeForSelectorPercent > 0.970f && actualRotationDegreeForSelectorPercent < 0.972f -> -17f
        actualRotationDegreeForSelectorPercent > 0.968f && actualRotationDegreeForSelectorPercent < 0.970f -> -15f
        actualRotationDegreeForSelectorPercent > 0.966f && actualRotationDegreeForSelectorPercent < 0.968f -> -13f
        actualRotationDegreeForSelectorPercent > 0.964f && actualRotationDegreeForSelectorPercent < 0.966f -> -11f
        actualRotationDegreeForSelectorPercent > 0.962f && actualRotationDegreeForSelectorPercent < 0.964f -> -9f
        actualRotationDegreeForSelectorPercent > 0.960f && actualRotationDegreeForSelectorPercent < 0.962f -> -7f
        actualRotationDegreeForSelectorPercent > 0.958f && actualRotationDegreeForSelectorPercent < 0.960f -> -5f
        else -> 0f
    }
}