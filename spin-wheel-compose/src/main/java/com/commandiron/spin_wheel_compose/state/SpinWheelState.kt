package com.commandiron.spin_wheel_compose.state

import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.*

data class SpinWheelState(
    val size: Dp,
    val frameWidth: Dp,
    val frameColor: Color,
    val dividerColor: Color,
    val selectorWidth: Dp,
    val selectorColor: Color,
    val pieColors: List<Color>,
    val pieCount: Int,
    val durationMillis: Int,
    val delayMillis: Int,
    val rotationPerSecond: Float,
    val easing: Easing,
    val startDegree: Float,
    val resultDegree: Float? = null,
    val autoResetDelay: Long? = null,
    val onFinish: () -> Unit,
) {
    var rotation by mutableStateOf(startDegree)
    private var spinState by mutableStateOf(SpinState.STOPPED)

    private fun generateRandomRotationDegree(): Float {
        return Random().nextInt(360).toFloat()
    }

    suspend fun spin() {
        animate(
            initialValue = startDegree,
            targetValue = (360f * rotationPerSecond * (durationMillis / 1000)) + (resultDegree ?: generateRandomRotationDegree()),
            animationSpec = tween(
                durationMillis = durationMillis,
                delayMillis = delayMillis,
                easing = easing
            ),
            block = { value,_ ->
                rotation = value
            }
        )
    }
}

enum class SpinState {
    STOPPED, SPINNING
}

@Composable
fun rememberSpinWheelState(
    size: Dp = 240.dp,
    frameWidth: Dp = 10.dp,
    selectorWidth: Dp = 12.dp,
    frameColor: Color = Color(0xFF941c2f),
    dividerColor: Color = Color.White,
    selectorColor: Color = Color(0xFFFF0000),
    pieColors: List<Color> =  listOf(
        Color(0xFFef476f),
        Color(0xFFf78c6b),
        Color(0xFFffd166),
        Color(0xFF83d483),
        Color(0xFF06d6a0),
        Color(0xFF0cb0a9),
        Color(0xFF118ab2),
        Color(0xFF073b4c)
    ),
    pieCount: Int = 8,
    durationMillis: Int = 12000,
    delayMillis: Int = 0,
    rotationPerSecond: Float = 1f,
    easing: Easing = CubicBezierEasing(0.16f, 1f, 0.3f, 1f),
    startDegree: Float = 0f,
    resultDegree: Float? = null,
    autoResetDelay: Long? = null,
    onFinish: () -> Unit
): SpinWheelState {
    return remember {
        SpinWheelState(
            size,
            frameWidth,
            frameColor,
            dividerColor,
            selectorWidth,
            selectorColor,
            pieColors,
            pieCount,
            durationMillis,
            delayMillis,
            rotationPerSecond,
            easing,
            startDegree,
            resultDegree,
            autoResetDelay,
            onFinish
        )
    }
}
