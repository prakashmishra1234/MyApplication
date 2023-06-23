package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;


public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try{
            if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Log.d("MyApplication Logs for services", "Broadcast Receiver Started");

                    // Starting foreground service after restarting device
                    /*
                    Intent serviceIntent = new Intent(context, MyForegroundService.class);
                    context.startForegroundService(serviceIntent);
                    */
                }
            }
        }catch(NullPointerException e){
            Log.d("MyApplication Logs for services", "Broadcast receiver catch block");
            e.printStackTrace();
        }
    }
}