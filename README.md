# SpinwheelCompose [![](https://jitpack.io/v/commandiron/SpinWheelCompose.svg)](https://jitpack.io/#commandiron/SpinWheelCompose)

SpinWheel in Android using Jetpack Compose.

## How it looks
<img src="art/spinwheel_gif.gif" width="250" height="530">

## Usage
```kotlin  
val textList by remember { 
    mutableStateOf(
        listOf("Pie 1", "Pie 2", "Pie 3", "Pie 4", "Pie 5", "Pie 6", "Pie 7", "Pie 8")
    )
}
DefaultSpinWheel(isSpinning = true){ pieIndex ->
    Text(text = textList[pieIndex])
}
```

## Setup
1. Open the file `settings.gradle` (it looks like that)
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // add jitpack here 👇🏽
        maven { url 'https://jitpack.io' }
       ...
    }
} 
...
```
2. Sync the project
3. Add dependency
```groovy
dependencies {
        implementation 'com.github.commandiron:SpinWheelCompose:1.0.2'
}
```

## Features

```kotlin  
val iconList by remember {
    mutableStateOf(
        listOf(
            Icons.Default.Star,
            Icons.Default.Star,
            Icons.Default.Star,
            Icons.Default.Star,
        )
    )
}
var isSpinning by remember { mutableStateOf(false)}
DefaultSpinWheel(
    dimensions = SpinWheelDefaults.spinWheelDimensions(
        spinWheelSize = 240.dp,
        frameWidth = 20.dp,
        selectorWidth = 12.dp
    ),
    colors = SpinWheelDefaults.spinWheelColors(
        frameColor = Color(0xFF5B5B5B),
        dividerColor = Color.White,
        selectorColor = Color(0xFF000000)
    ),
    animationAttr = SpinWheelDefaults.spinWheelAnimationAttr(
        pieCount = 4,
        durationMillis = 3000,
        delayMillis = 200,
        rotationPerSecond = 2f,
        easing = FastOutLinearInEasing,
        startDegree = 90f
    ),
    isSpinning = isSpinning,
    onClick = { isSpinning = !isSpinning },
    onFinish = { isSpinning = false }
){ pieIndex ->
    Icon(
        imageVector = iconList[pieIndex],
        tint = Color.White,
        contentDescription = null
    )
}
```
