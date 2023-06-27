package com.example.myapplication;

import android.os.Build;

public class DeviceUtils {
    public static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }
    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getDeviceName() {
        String manufacturer = getDeviceManufacturer();
        String model = getDeviceModel();
        if (model.startsWith(manufacturer)) {
            return model;
        } else {
            return manufacturer + " " + model;
        }
    }
}
