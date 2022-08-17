# SpinWheelCompose
Spinwheel in Android using Jetpack Compose.

## How it looks
<img src="art/spinwheel_gif.gif" width="250" height="530">

## Usage
```kotlin  
var isSpinning by remember { mutableStateOf(false)}
DefaultSpinWheel(
    isSpinning = isSpinning,
    resultDegree = resultDegree,
    onClick = {
        if(!isSpinning){
            resultDegree = Random().nextInt(360).toFloat()
        }
        isSpinning = !isSpinning
    },
    onFinish = {
        isSpinning = false
    }
)
```

## Setup
