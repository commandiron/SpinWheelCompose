package com.commandiron.spin_wheel_compose.state

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.*

data class SpinWheelState(
    val durationMillis: Int,
    val delayMillis: Int,
    val rotationPerSecond: Float,
    val easing: Easing,
    val startDegree: Float,
    val resultDegree: Float? = null,
    val autoResetDelay: Long? = null
) {
    var rotation by mutableStateOf(startDegree)
    private var spinState by mutableStateOf(SpinState.STOPPED)

    suspend fun spin(onAnimation: (spinState: SpinState) -> Unit) {
        when(spinState) {
            SpinState.STOPPED ->  {
                spinState = SpinState.SPINNING
                onAnimation(spinState)

                animate(
                    initialValue = startDegree,
                    targetValue = (360f * rotationPerSecond * (durationMillis / 1000)) + (resultDegree ?: generateRandomRotationDegree()),
                    animationSpec = tween(
                        durationMillis = durationMillis,
                        delayMillis = delayMillis,
                        easing = easing
                    ),
                    block = { value, _ ->
                        rotation = value
                    }
                )

                spinState = SpinState.STOPPED
                onAnimation(spinState)
            }
            SpinState.SPINNING -> {


            }
        }
    }

    private fun generateRandomRotationDegree(): Float {
        return Random().nextInt(360).toFloat()
    }
}

enum class SpinState {
    STOPPED, SPINNING
}

@Composable
fun rememberSpinWheelState(
    durationMillis: Int = 12000,
    delayMillis: Int = 0,
    rotationPerSecond: Float = 1f,
    easing: Easing = CubicBezierEasing(0.16f, 1f, 0.3f, 1f),
    startDegree: Float = 0f,
    resultDegree: Float? = null,
    autoResetDelay: Long? = null
): SpinWheelState {
    return remember {
        SpinWheelState(
            durationMillis,
            delayMillis,
            rotationPerSecond,
            easing,
            startDegree,
            resultDegree,
            autoResetDelay
        )
    }
}
