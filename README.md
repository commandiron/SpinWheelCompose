# SpinwheelCompose
SpinWheel in Android using Jetpack Compose.

## How it looks
<img src="art/spinwheel_gif.gif" width="250" height="530">

## Usage - Example
```kotlin  
var isSpinning by remember { mutableStateOf(false) }
LaunchedEffect(key1 = isSpinning){
    if(!isSpinning){
        resultDegree = Random().nextInt(360).toFloat()
    }
}
DefaultSpinWheel(
    isSpinning = isSpinning,
    resultDegree = resultDegree,
    onClick = { isSpinning = !isSpinning },
    onFinish = { isSpinning = false }
)
```

## Setup
