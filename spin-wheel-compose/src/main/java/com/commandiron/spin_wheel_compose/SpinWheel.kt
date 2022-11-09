package com.commandiron.spin_wheel_compose

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.commandiron.spin_wheel_compose.state.SpinWheelState

@Composable
fun SpinWheel(
    modifier: Modifier = Modifier,
    state: SpinWheelState,
    onClick: () -> Unit,
    content: @Composable BoxScope.(pieIndex: Int) -> Unit
){
    SpinWheelSelector(
        modifier = modifier,
        frameSize = state.size,
        pieCount = state.pieCount,
        selectorWidth = state.selectorWidth,
        selectorColor = state.selectorColor,
        rotationDegree = state.rotation
    ) {
        SpinWheelFrame(
            modifier = modifier,
            frameSize = state.size - state.selectorWidth,
            pieCount = state.pieCount,
            frameWidth = state.frameWidth,
            frameColor = state.frameColor,
            dividerColor =  state.dividerColor,
            rotationDegree = state.rotation,
            onClick = onClick,
        ) {
            SpinWheelPies(
                modifier = modifier,
                spinSize = state.size - state.frameWidth - state.selectorWidth,
                pieCount = state.pieCount,
                pieColors = state.pieColors,
                rotationDegree = state.rotation,
                onClick = onClick
            ){
                SpinWheelContent(
                    modifier = modifier,
                    spinSize = state.size - state.frameWidth - state.selectorWidth,
                    pieCount = state.pieCount,
                    rotationDegree = state.rotation
                ){
                    content(it)
                }
            }
        }
    }
}
