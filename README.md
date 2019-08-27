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

## Example
An example of not allowing a user to use your app unless they have granted GPS access

```javascript
import React, { Component } from 'react';
import { PermissionsAndroid, Platform, Alert, View, Text } from 'react-native';
import AndroidOpenSettings from 'react-native-android-open-settings-async'

class App extends Component {
    async componentDidMount() {
        if (Platform.OS == "android") {
            const title = "Location Permission Required";
            const message = "In order to use this app you must grant location access";
            const permission = "android.permission.ACCESS_FINE_LOCATION";

            while (!(await PermissionsAndroid.check(permission))) {
                var permissionResponse = await PermissionsAndroid.request(permission, {
                    title: title,
                    message: message,
                    buttonPositive: "Ok"
                });

                if (permissionResponse == "granted") {
                    break;
                }
                else if (permissionResponse == "never_ask_again") {
                    await new Promise((resolve) => {
                        const alertButtons = [{
                            text: "Open Settings",
                            onPress: () => {
                                AndroidOpenSettings.appDetailsSettings().then(() => {
                                    resolve("yes")
                                })
                            },
                        }]
                        Alert.alert(title, message, alertButtons,
                            {
                                cancelable: false,
                                onDismiss: () => resolve("yes")
                            })
                    })
                }
            }
        }

        //Continue with component did mount, you have permission now
    }

    render() {
        return <View><Text>You now have permission (this won't render unless you do)</Text></View>
    }
}
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
