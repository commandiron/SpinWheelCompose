package com.commandiron.spin_wheel_compose

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.commandiron.spin_wheel_compose.state.SpinWheelState

@Composable
fun AnimatedSpinWheel(
    modifier: Modifier = Modifier,
    state: SpinWheelState,
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
