# MVPCAndroid
Model-View-Presenter-Clean Architecture for Android
Retain presenters in Loaders

# Interation

On JCenter: 

**build.gradle** 
```gradle
compile 'corp.wmsoft.android.lib:mvpcandroid:0.0.4'
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
