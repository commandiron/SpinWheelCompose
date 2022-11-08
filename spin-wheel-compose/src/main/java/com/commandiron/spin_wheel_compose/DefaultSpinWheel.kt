package com.commandiron.spin_wheel_compose

import androidx.annotation.IntRange
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DefaultSpinWheel(
    modifier: Modifier = Modifier,
    dimensions: SpinWheelDimensions = SpinWheelDefaults.spinWheelDimensions(),
    colors: SpinWheelColors = SpinWheelDefaults.spinWheelColors(),
    animationAttr: SpinWheelAnimationAttr = SpinWheelDefaults.spinWheelAnimationAttr(),
    isSpinning: Boolean = false,
    resultDegree: Float = 30f,
    onClick: () -> Unit = {},
    onFinish: (resultIndex: Int) -> Unit = {},
    content: @Composable BoxScope.(pieIndex: Int) -> Unit
) {
    AnimatedSpinWheel(
        modifier = modifier,
        size = dimensions.spinWheelSize().value,
        frameWidth = dimensions.frameWidth().value,
        frameColor = colors.frameColor().value,
        dividerColor = colors.dividerColor().value,
        selectorWidth = dimensions.selectorWidth().value,
        selectorColor = colors.selectorColor().value,
        pieCount = animationAttr.pieCount().value,
        pieColors = colors.pieColors().value,
        durationMillis = animationAttr.durationMillis().value,
        delayMillis = animationAttr.delayMillis().value,
        rotationPerSecond = animationAttr.rotationPerSecond().value,
        easing = animationAttr.easing().value,
        startDegree = animationAttr.startDegree().value,
        autoResetDelay = animationAttr.autoResetDelay().value,
        isSpinning = isSpinning,
        resultDegree = resultDegree,
        onClick = onClick,
        onFinish = onFinish,
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
    @Composable
    fun spinWheelAnimationAttr(
        @IntRange(from = 2, to = 8) pieCount: Int = 8,
        durationMillis: Int = 12000,
        delayMillis: Int = 0,
        rotationPerSecond: Float = 1f,
        easing: Easing = CubicBezierEasing(0.16f, 1f, 0.3f, 1f),
        startDegree: Float = 0f,
        autoResetDelay: Long = 1000
    ): SpinWheelAnimationAttr = DefaultSpinWheelAnimationAttr(
        pieCount = pieCount,
        durationMillis = durationMillis,
        delayMillis = delayMillis,
        rotationPerSecond = rotationPerSecond,
        easing = easing,
        startDegree = startDegree,
        autoResetDelay = autoResetDelay
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

interface SpinWheelAnimationAttr {
    @Composable
    fun pieCount(): State<Int>
    @Composable
    fun durationMillis(): State<Int>
    @Composable
    fun delayMillis(): State<Int>
    @Composable
    fun rotationPerSecond(): State<Float>
    @Composable
    fun easing(): State<Easing>
    @Composable
    fun startDegree(): State<Float>
    @Composable
    fun autoResetDelay(): State<Long>
}

@Immutable
private class DefaultSpinWheelAnimationAttr(
    private val pieCount: Int,
    private val durationMillis: Int,
    private val delayMillis: Int,
    private val rotationPerSecond: Float,
    private val easing: Easing,
    private val startDegree: Float,
    private val autoResetDelay: Long
) : SpinWheelAnimationAttr {
    @Composable
    override fun pieCount(): State<Int> {
        return rememberUpdatedState(pieCount)
    }
    @Composable
    override fun durationMillis(): State<Int> {
        return rememberUpdatedState(durationMillis)
    }
    @Composable
    override fun delayMillis(): State<Int> {
        return rememberUpdatedState(delayMillis)
    }
    @Composable
    override fun rotationPerSecond(): State<Float> {
        return rememberUpdatedState(rotationPerSecond)
    }
    @Composable
    override fun easing(): State<Easing> {
        return rememberUpdatedState(easing)
    }
    @Composable
    override fun startDegree(): State<Float> {
        return rememberUpdatedState(startDegree)
    }
    @Composable
    override fun autoResetDelay(): State<Long> {
        return rememberUpdatedState(autoResetDelay)
    }
}

