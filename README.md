# MVPCAndroid
Model-View-Presenter-Clean Architecture for Android
Retain presenters in Loaders
Iteractors with RxJava

**Under development**
**Work in progress**

# Integration

On JCenter: 

**build.gradle** 
```gradle
compile 'corp.wmsoft.android.lib:mvpcandroid:+'
```

Or like library project: 

**settings.gradle** 
```gradle
include ':mvpcandroid'
project(':mvpcandroid').projectDir = new File(settingsDir, '../MVPCAndroid/mvpcandroid')
```
project **build.gradle** dependencies 
```gradle
compile project(':mvpcandroid')
```
