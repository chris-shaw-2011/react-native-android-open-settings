package com.levelasquez.androidopensettings;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.ArrayList;
import java.util.List;

class AndroidOpenSettings extends ReactContextBaseJavaModule {

    private final List<Integer> usedRequestCodes = new ArrayList<>();
    private final ReactContext reactContext;

    @SuppressWarnings("unused")
    AndroidOpenSettings(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override @NonNull
    public String getName() {
        return "RNAndroidOpenSettings";
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void generalSettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void homeSettings(Promise promise) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(Settings.ACTION_HOME_SETTINGS);
            startActivity(intent, promise);
        }
        else {
            promise.reject("1", "Android version not supported");
        }
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void appDetailsSettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + reactContext.getPackageName()));

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void wifiSettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void locationSourceSettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void wirelessSettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void airplaneModeSettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void apnSettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_APN_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void bluetoothSettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void dateSettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_DATE_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void localeSettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void inputMethodSettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void displaySettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_DISPLAY_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void securitySettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void internalStorageSettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void memoryCardSettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_MEMORY_CARD_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void accessibilitySettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void applicationSettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void deviceInfoSettings(Promise promise) {
        Intent intent = new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS);

        startActivity(intent, promise);
    }

    @SuppressWarnings("unused")
    @ReactMethod
    public void appNotificationSettings(Promise promise) {
        Intent intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS"); // Settings.ACTION_APP_NOTIFICATION_SETTINGS

        // for Android 5-7
        intent.putExtra("app_package", reactContext.getPackageName());
        intent.putExtra("app_uid", reactContext.getApplicationInfo().uid);

        // for Android 8 and above
        intent.putExtra("android.provider.extra.APP_PACKAGE", reactContext.getPackageName()); // Settings.EXTRA_APP_PACKAGE

        startActivity(intent, promise);
    }

    private void startActivity(Intent intent, final Promise promise) {
        if (intent.resolveActivity(reactContext.getPackageManager()) != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            Activity currentActivity = getCurrentActivity();
            final int requestCode = usedRequestCodes.size() == 0 ? 1
                    : usedRequestCodes.get(usedRequestCodes.size() - 1) + 1;

            usedRequestCodes.add(requestCode);

            reactContext.addActivityEventListener(new ActivityEventListener() {
                @Override
                public void onActivityResult(Activity activity, int resultRequestCode, int resultCode, Intent data) {
                    if (requestCode == resultRequestCode) {
                        reactContext.removeActivityEventListener(this);

                        usedRequestCodes.remove(Integer.valueOf(requestCode));

                        reactContext.addLifecycleEventListener(new LifecycleEventListener() {
                            boolean hasBeenPaused;

                            @Override
                            public void onHostResume() {
                                if (hasBeenPaused) {
                                    reactContext.removeLifecycleEventListener(this);
                                    promise.resolve(null);
                                }
                            }

                            @Override
                            public void onHostPause() {
                                hasBeenPaused = true;
                            }

                            @Override
                            public void onHostDestroy() {

                            }
                        });
                    }
                }

                @Override
                public void onNewIntent(Intent intent) {
                }
            });

            if (currentActivity != null) {
                currentActivity.startActivityForResult(intent, requestCode);
            } else {
                promise.reject("2", "Null currentActivity");
            }
        } else {
            promise.reject("1", "Null return from resolveActivity");
        }
    }
}