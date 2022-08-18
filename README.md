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
        implementation 'com.github.commandiron:SpinWheelCompose:1.0.3'
}
```

## Features

<table>
<tr>
<td>
            
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
        spinWheelSize = 180.dp,
        frameWidth = 40.dp,
        selectorWidth = 24.dp
    ),
    colors = SpinWheelDefaults.spinWheelColors(
        frameColor = Color(0xFF370617),
        dividerColor = Color.White,
        selectorColor = Color(0xFFd00000)
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
</td>
<td>
            
<img src="https://user-images.githubusercontent.com/50905347/185386346-082e5adc-0a55-4619-8581-ff5c4c1d4a37.png" width="250" height="530">
    
</td>
</tr>
</table>
