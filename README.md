# SpinwheelCompose [![](https://jitpack.io/v/commandiron/SpinWheelCompose.svg)](https://jitpack.io/#commandiron/SpinWheelCompose)

SpinWheel in Android using Jetpack Compose.

## How it looks
<img src="art/spinwheel.gif" width="250" height="530">

## Usage
```kotlin  
val textList by remember {
    mutableStateOf(
        listOf("Pie 1", "Pie 2", "Pie 3", "Pie 4", "Pie 5", "Pie 6", "Pie 7", "Pie 8")
    )
}

val state = rememberSpinWheelState()
val scope = rememberCoroutineScope()

SpinWheel(
    state = state,
    onClick = { scope.launch { state.animate() } }
){ pieIndex ->
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
        implementation 'com.github.commandiron:SpinWheelCompose:1.1.0'
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
repeat(3){
    SpinWheel(
        state = rememberSpinWheelState(
            pieCount = 4,
            durationMillis = 20000,
            delayMillis = 200,
            rotationPerSecond = 2f,
            easing = LinearOutSlowInEasing,
            startDegree = 90f,
            resultDegree = 212f,
            autoSpinDelay = 0
        ),
        dimensions = SpinWheelDefaults.spinWheelDimensions(
            spinWheelSize = 180.dp,
            frameWidth = 20.dp,
            selectorWidth = 10.dp
        ),
        colors = SpinWheelDefaults.spinWheelColors(
            frameColor = Color(0xFF403d39),
            dividerColor = Color(0xFFfffcf2),
            selectorColor = Color(0xFFdc0073),
            pieColors = listOf(
                Color(0xFFdabfff),
                Color(0xFF907ad6),
                Color(0xFF4f518c),
                Color(0xFF2c2a4a)
            )
        )
    ){ pieIndex ->
        Icon(
            imageVector = iconList[pieIndex],
            tint = Color.White,
            contentDescription = null
        )
    }
    Spacer(modifier = Modifier.height(32.dp))
}
```   
</td>
<td>
            
<img src="art/spinwheel_2.gif" width="250" height="530">
    
</td>
</tr>
</table>
