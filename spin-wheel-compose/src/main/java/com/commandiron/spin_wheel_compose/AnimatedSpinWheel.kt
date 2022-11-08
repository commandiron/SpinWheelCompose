package com.commandiron.spin_wheel_compose

import androidx.annotation.IntRange
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.delay

@Composable
internal fun AnimatedSpinWheel(
    modifier: Modifier,
    size: Dp,
    frameWidth: Dp,
    frameColor: Color,
    dividerColor: Color,
    selectorWidth: Dp,
    selectorColor: Color,
    @IntRange(from = 2, to = 8) pieCount: Int,
    pieColors: List<Color>,
    durationMillis: Int,
    delayMillis: Int,
    rotationPerSecond: Float,
    easing: Easing,
    startDegree: Float,
    isSpinning: Boolean,
    resultDegree: Float,
    autoResetDelay: Long = 0,
    onClick: () -> Unit,
    onFinish: (resultIndex: Int) -> Unit,
    content: @Composable BoxScope.(pieIndex: Int) -> Unit
){
    val actualStartDegree = startDegree + 90f
    val actualResultDegree = resultDegree + 90f
    val rotationDegree = remember {
        Animatable(actualStartDegree)
    }

    var animationResult by remember {
        mutableStateOf<AnimationResult<Float, AnimationVector1D>?>(null)
    }
    var spin by remember { mutableStateOf(false) }
    LaunchedEffect(isSpinning, spin){
        while (isSpinning) {
            val targetValue = 360f * rotationPerSecond * (durationMillis / 1000) + actualResultDegree
            animationResult = rotationDegree.animateTo(
                targetValue = targetValue,
                animationSpec = tween(
                    durationMillis = durationMillis,
                    delayMillis = delayMillis,
                    easing = easing
                )
            )
        }
    }
    LaunchedEffect(animationResult) {
        animationResult?.let {
            if(it.endReason.name == "Finished"){
                val pieDegree = 360f / pieCount // 45
                val quotient = resultDegree.toInt() / pieDegree.toInt()
                val resultIndex = pieCount - quotient - 1

                onFinish(resultIndex)
                if(autoResetDelay != 0L) {
                    delay(autoResetDelay)
                    rotationDegree.snapTo(actualStartDegree)
                    spin = !spin
                } else {
                    rotationDegree.snapTo(actualResultDegree)
                }
            }
        }
    }
    SpinWheelSelector(
        modifier = modifier,
        frameSize = size,
        pieCount = pieCount,
        selectorWidth = selectorWidth,
        selectorColor = selectorColor,
        rotationDegree = rotationDegree.value
    ) {
        SpinWheelFrame(
            modifier = modifier,
            frameSize = size - selectorWidth,
            pieCount = pieCount,
            frameWidth = frameWidth,
            frameColor = frameColor,
            dividerColor =  dividerColor,
            rotationDegree = rotationDegree.value,
            onClick = onClick,
        ) {
            SpinWheelPies(
                modifier = modifier,
                spinSize = size - frameWidth - selectorWidth,
                pieCount = pieCount,
                pieColors = pieColors,
                rotationDegree = rotationDegree.value,
                onClick = onClick
            ){
                SpinWheelContent(
                    modifier = modifier,
                    spinSize = size - frameWidth - selectorWidth,
                    pieCount = pieCount,
                    rotationDegree = rotationDegree.value
                ){
                    content(it)
                }
            }
        }
    }
}
