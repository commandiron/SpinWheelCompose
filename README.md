# SpinwheelCompose [![](https://jitpack.io/v/commandiron/SpinWheelCompose.svg)](https://jitpack.io/#commandiron/SpinWheelCompose)

SpinWheel in Android using Jetpack Compose.

## How it looks
<img src="art/spinwheel_gif.gif" width="250" height="530">

## Usage - Example
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
        implementation 'com.github.commandiron:SpinWheelCompose:1.0'
}
```
