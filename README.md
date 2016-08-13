# MVPCAndroid
Model-View-Presenter-Clean Architecture for Android
Retain presenters in Loaders

# Interation

JCenter:
**build.gradle**
```gradle
compile 'corp.wmsoft.android.lib:mvpcandroid:0.0.4'
```

or like library project:
**settings.gradle**
```gradle
include ':mvpcandroid'
project(':mvpcandroid').projectDir = new File(settingsDir, '../MVPCAndroid/mvpcandroid')
```
project **build.gradle** dependencies
```gradle
compile project(':mvpcandroid')
```
