package com.commandiron.spin_wheel_compose

import androidx.annotation.IntRange
import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

@Composable
fun AnimatedSpinWheel(
    modifier: Modifier,
    spinSize: Dp,
    @IntRange(from = 2, to = 8) pieCount: Int,
    titleList: List<String>,
    titleTextStyle: TextStyle,
    borderWidth: Dp,
    borderColor: Color,
    dividerColor: Color,
    durationMillis: Int,
    delayMillis: Int,
    rotationPerSecond: Int,
    startDegree: Float,
    resultDegree: Float,
    easing: Easing,
    isSpinning: Boolean,
    onClickEnabled: Boolean,
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
            animationResult = rotationDegree.animateTo(
                targetValue = 360f * rotationPerSecond * (durationMillis / 1000) + resultDegree,
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
    SpinWheelFrame(
        modifier = modifier,
        spinSize = spinSize,
        pieCount = pieCount,
        rotationDegree = rotationDegree.value,
        onClickEnabled = onClickEnabled,
        onClick = onClick,
        borderWidth = borderWidth,
        borderColor = borderColor,
        dividerColor =  dividerColor
    ) {
        SpinWheelPies(
            modifier = modifier,
            spinSize = spinSize - borderWidth,
            pieCount = pieCount,
            rotationDegree = rotationDegree.value,
            onClickEnabled = onClickEnabled,
            onClick = onClick
        ){
            SpinWheelContent(
                modifier = modifier,
                spinSize = spinSize - borderWidth,
                pieCount = pieCount,
                rotationDegree = rotationDegree.value,
                titleList = titleList,
                titleTextStyle = titleTextStyle
            )
        }
    }
}
