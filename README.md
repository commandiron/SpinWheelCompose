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
        implementation 'com.github.commandiron:SpinWheelCompose:1.0'
}
```
