package com.example.myapplication;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class ForeGroundUtil {
    public static void startForegroundService (Context context){
        Intent serviceIntent = new Intent(context.getApplicationContext(), MyForegroundService.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Log.d("MyApplication Logs for services", "Foreground service started by start foreground service");
            context.startForegroundService(serviceIntent);
        }else{
            Log.d("MyApplication Logs for services", "Foreground service started by start service");
            context.startService(serviceIntent);
        }
    }
}
