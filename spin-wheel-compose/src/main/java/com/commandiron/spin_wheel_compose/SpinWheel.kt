package com.commandiron.spin_wheel_compose

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.commandiron.spin_wheel_compose.state.SpinWheelState
import com.commandiron.spin_wheel_compose.state.rememberSpinWheelState

@Composable
fun SpinWheel(
    modifier: Modifier = Modifier,
    state: SpinWheelState = rememberSpinWheelState(),
    dimensions: SpinWheelDimensions = SpinWheelDefaults.spinWheelDimensions(),
    colors: SpinWheelColors = SpinWheelDefaults.spinWheelColors(),
    onClick: () -> Unit = {},
    content: @Composable BoxScope.(pieIndex: Int) -> Unit
) {
    AnimatedSpinWheel(
        modifier = modifier,
        state = state,
        size = dimensions.spinWheelSize().value,
        frameWidth = dimensions.frameWidth().value,
        selectorWidth = dimensions.selectorWidth().value,
        frameColor = colors.frameColor().value,
        dividerColor = colors.dividerColor().value,
        selectorColor = colors.selectorColor().value,
        pieColors = colors.pieColors().value,
        onClick = onClick,
        content = content
    )
}

object SpinWheelDefaults{
    @Composable
    fun spinWheelColors(
        frameColor: Color = Color(0xFF941c2f),
        dividerColor: Color = Color.White,
        selectorColor: Color = Color(0xFFFF0000),
        pieColors: List<Color> = listOf(
            Color(0xFFef476f),
            Color(0xFFf78c6b),
            Color(0xFFffd166),
            Color(0xFF83d483),
            Color(0xFF06d6a0),
            Color(0xFF0cb0a9),
            Color(0xFF118ab2),
            Color(0xFF073b4c)
        )
    ): SpinWheelColors = DefaultSpinWheelColors(
        frameColor = frameColor,
        dividerColor = dividerColor,
        selectorColor = selectorColor,
        pieColors = pieColors
    )

    @Composable
    fun spinWheelDimensions(
        spinWheelSize: Dp = 240.dp,
        frameWidth: Dp = 10.dp,
        selectorWidth: Dp = 12.dp,
    ): SpinWheelDimensions = DefaultSpinWheelDimensions(
        spinWheelSize = spinWheelSize,
        frameWidth = frameWidth,
        selectorWidth = selectorWidth
    )
}

interface SpinWheelColors {
    @Composable
    fun frameColor(): State<Color>
    @Composable
    fun dividerColor(): State<Color>
    @Composable
    fun selectorColor(): State<Color>
    @Composable
    fun pieColors(): State<List<Color>>
}

@Immutable
private class DefaultSpinWheelColors(
    private val frameColor: Color,
    private val dividerColor: Color,
    private val selectorColor: Color,
    private val pieColors: List<Color>
) : SpinWheelColors {

    @Composable
    override fun frameColor(): State<Color> {
        return rememberUpdatedState(frameColor)
    }

    @Composable
    override fun dividerColor(): State<Color> {
        return rememberUpdatedState(dividerColor)
    }

    @Composable
    override fun selectorColor(): State<Color> {
        return rememberUpdatedState(selectorColor)
    }

    @Composable
    override fun pieColors(): State<List<Color>> {
        return rememberUpdatedState(pieColors)
    }
}

interface SpinWheelDimensions {
    @Composable
    fun spinWheelSize(): State<Dp>
    @Composable
    fun frameWidth(): State<Dp>
    @Composable
    fun selectorWidth(): State<Dp>
}

@Immutable
private class DefaultSpinWheelDimensions(
    private val spinWheelSize: Dp,
    private val frameWidth: Dp,
    private val selectorWidth: Dp
) : SpinWheelDimensions {

    @Composable
    override fun spinWheelSize(): State<Dp> {
        return rememberUpdatedState(spinWheelSize)
    }

    @Composable
    override fun frameWidth(): State<Dp> {
        return rememberUpdatedState(frameWidth)
    }

    @Composable
    override fun selectorWidth(): State<Dp> {
        return rememberUpdatedState(selectorWidth)
    }
}