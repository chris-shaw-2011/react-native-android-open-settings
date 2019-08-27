# react-native-android-open-settings-async

Open android settings from your react native app, this is a forked version of levelasquez/react-native-android-open-settings because that appears to be abandoned

## Install
Using npm

```
npm install react-native-android-open-settings-async --save
```

Using yarn
```
yarn add react-native-android-open-settings-async
```

## Usage

```javascript

import AndroidOpenSettings from 'react-native-android-open-settings'

// Open general settings menu
await AndroidOpenSettings.generalSettings()

// Open home screen settings menu
await AndroidOpenSettings.homeSettings()

// Open app settings menu
await AndroidOpenSettings.appDetailsSettings()

// Open wifi settings menu
await AndroidOpenSettings.wifiSettings()

// Open location source settings menu
await AndroidOpenSettings.locationSourceSettings()

// Open wireless settings menu
await AndroidOpenSettings.wirelessSettings()

// Open airplane mode settings menu
await AndroidOpenSettings.airplaneModeSettings()

// Open apn settings menu
await AndroidOpenSettings.apnSettings()

// Open bluetooth settings menu
await AndroidOpenSettings.bluetoothSettings()

// Open date settings menu
await AndroidOpenSettings.dateSettings()

// Open locale settings menu
await AndroidOpenSettings.localeSettings()

// Open input method settings menu
await AndroidOpenSettings.inputMethodSettings()

// Open display settings menu
await AndroidOpenSettings.displaySettings()

// Open security settings menu
await AndroidOpenSettings.securitySettings()

// Open internal storage settings menu
await AndroidOpenSettings.internalStorageSettings()

// Open memory card settings menu
await AndroidOpenSettings.memoryCardSettings()

// Open accessibility settings menu
await AndroidOpenSettings.accessibilitySettings()

// Open application settings menu
await AndroidOpenSettings.applicationSettings()

// Open device info settings menu
await AndroidOpenSettings.deviceInfoSettings()

// Open application notification settings menu
await AndroidOpenSettings.appNotificationSettings()
```
