package com.commandiron.spin_wheel_compose

import androidx.annotation.IntRange
import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.launch

@Composable
fun AnimatedSpinWheel(
    modifier: Modifier,
    size: Dp,
    titleList: List<String>,
    titleTextStyle: TextStyle,
    @IntRange(from = 2, to = 8) pieCount: Int,
    frameWidth: Dp,
    frameColor: Color,
    dividerColor: Color,
    selectorWidth: Dp,
    selectorColor: Color,
    isSpinning: Boolean,
    durationMillis: Int,
    delayMillis: Int,
    rotationPerSecond: Float,
    easing: Easing,
    startDegree: Float,
    resultDegree: Float,
    onClick: () -> Unit,
    onFinish: () -> Unit
){
    val rotationDegree = remember {
        Animatable(startDegree)
    }

    var animationResult by remember {
        mutableStateOf<AnimationResult<Float, AnimationVector1D>?>(null)
    }
    LaunchedEffect(isSpinning){
        if(isSpinning){
            val targetValue = 360f * rotationPerSecond * (durationMillis / 1000) + resultDegree
            println(targetValue)
            animationResult = rotationDegree.animateTo(
                targetValue = targetValue,
                animationSpec = tween(
                    durationMillis = durationMillis,
                    delayMillis = delayMillis,
                    easing = easing
                )
            )
            animationResult?.let {
                if(it.endReason.name == "Finished"){
                    rotationDegree.snapTo(resultDegree)
                    onFinish()
                }
            }
        }else{
            rotationDegree.snapTo(resultDegree)
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
                rotationDegree = rotationDegree.value,
                onClick = onClick
            ){
                SpinWheelContent(
                    modifier = modifier,
                    spinSize = size - frameWidth - selectorWidth,
                    titleList = titleList,
                    titleTextStyle = titleTextStyle,
                    pieCount = pieCount,
                    rotationDegree = rotationDegree.value
                )
            }
        }
    }
}
