package com.commandiron.spin_wheel_compose

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.commandiron.spin_wheel_compose.state.SpinWheelState
import com.commandiron.spin_wheel_compose.state.rememberSpinWheelState

@Composable
internal fun AnimatedSpinWheel(
    modifier: Modifier,
    state: SpinWheelState = rememberSpinWheelState(),
    onClick: () -> Unit,
    onFinish: (resultIndex: Int) -> Unit,
    content: @Composable BoxScope.(pieIndex: Int) -> Unit
){

    SpinWheelSelector(
        modifier = modifier,
        frameSize = state.size,
        pieCount = state.pieCount,
        selectorWidth = state.selectorWidth,
        selectorColor = state.selectorColor,
        rotationDegree = state.rotationDegree
    ) {
        SpinWheelFrame(
            modifier = modifier,
            frameSize = state.size - state.selectorWidth,
            pieCount = state.pieCount,
            frameWidth = state.frameWidth,
            frameColor = state.frameColor,
            dividerColor =  state.dividerColor,
            rotationDegree = state.rotationDegree,
            onClick = onClick,
        ) {
            SpinWheelPies(
                modifier = modifier,
                spinSize = state.size - state.frameWidth - state.selectorWidth,
                pieCount = state.pieCount,
                pieColors = state.pieColors,
                rotationDegree = state.rotationDegree,
                onClick = onClick
            ){
                SpinWheelContent(
                    modifier = modifier,
                    spinSize = state.size - state.frameWidth - state.selectorWidth,
                    pieCount = state.pieCount,
                    rotationDegree = state.rotationDegree
                ){
                    content(it)
                }
            }
        }
    }
}
