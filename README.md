# MVPCAndroid
Model-View-Presenter-Clean Architecture for Android

Retain presenters in Loaders

Iteractors with RxJava

**Under development**
**Work in progress**

# Integration

On jcenter: 

**build.gradle** 
```gradle
dependencies {
    compile 'corp.wmsoft.android.lib:mvpc_rx:+'
}
```

Or like library project: 

**settings.gradle** 
```gradle
include ':mvpc_rx'
project(':mvpc_rx').projectDir = new File(settingsDir, '../MVPCAndroid/mvpc_rx')
```
project **build.gradle** dependencies 
```gradle
compile project(':mvpc_rx')
```
