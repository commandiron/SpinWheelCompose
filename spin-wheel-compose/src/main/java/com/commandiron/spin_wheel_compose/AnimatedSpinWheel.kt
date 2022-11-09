package com.commandiron.spin_wheel_compose

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.commandiron.spin_wheel_compose.state.SpinWheelState
import kotlinx.coroutines.delay

@Composable
internal fun AnimatedSpinWheel(
    modifier: Modifier,
    state: SpinWheelState,
    size: Dp,
    frameWidth: Dp,
    selectorWidth: Dp,
    frameColor: Color,
    dividerColor: Color,
    selectorColor: Color,
    pieColors: List<Color>,
    pieCount: Int,
    onClick: () -> Unit,
    content: @Composable BoxScope.(pieIndex: Int) -> Unit
){
    LaunchedEffect(key1 = state.autoSpinDelay) {
        state.autoSpinDelay?.let {
            delay(it)
            state.spinToReset()
        }
    }
    SpinWheelSelector(
        modifier = modifier,
        frameSize = size,
        pieCount = pieCount,
        selectorWidth = selectorWidth,
        selectorColor = selectorColor,
        rotationDegree = state.rotation.value
    ) {
        SpinWheelFrame(
            modifier = modifier,
            frameSize = size - selectorWidth,
            pieCount = pieCount,
            frameWidth = frameWidth,
            frameColor = frameColor,
            dividerColor =  dividerColor,
            rotationDegree = state.rotation.value,
            onClick = onClick,
        ) {
            SpinWheelPies(
                modifier = modifier,
                spinSize = size - frameWidth - selectorWidth,
                pieCount = pieCount,
                pieColors = pieColors,
                rotationDegree = state.rotation.value,
                onClick = onClick
            ){
                SpinWheelContent(
                    modifier = modifier,
                    spinSize = size - frameWidth - selectorWidth,
                    pieCount = pieCount,
                    rotationDegree = state.rotation.value
                ){
                    content(it)
                }
            }
        }
    }
}
