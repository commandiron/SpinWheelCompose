package com.commandiron.spin_wheel_compose

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.commandiron.spin_wheel_compose.state.SpinWheelState

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
    SpinWheelSelector(
        modifier = modifier,
        frameSize = size,
        pieCount = pieCount,
        selectorWidth = selectorWidth,
        selectorColor = selectorColor,
        rotationDegree = state.rotation
    ) {
        SpinWheelFrame(
            modifier = modifier,
            frameSize = size - selectorWidth,
            pieCount = pieCount,
            frameWidth = frameWidth,
            frameColor = frameColor,
            dividerColor =  dividerColor,
            rotationDegree = state.rotation,
            onClick = onClick,
        ) {
            SpinWheelPies(
                modifier = modifier,
                spinSize = size - frameWidth - selectorWidth,
                pieCount = pieCount,
                pieColors = pieColors,
                rotationDegree = state.rotation,
                onClick = onClick
            ){
                SpinWheelContent(
                    modifier = modifier,
                    spinSize = size - frameWidth - selectorWidth,
                    pieCount = pieCount,
                    rotationDegree = state.rotation
                ){
                    content(it)
                }
            }
        }
    }
}
