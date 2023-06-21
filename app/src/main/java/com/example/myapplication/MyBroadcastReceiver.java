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
            Log.d("beforeBoot", "before boot");
            if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
                Log.d("beforeBoot", "after boot");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Log.d("beforeBoot", "above O");
                    Intent serviceIntent = new Intent(context, MyForegroundService.class);
                    context.startForegroundService(serviceIntent);
                }
            }
        }catch(NullPointerException e){
            Log.d("beforeBoot", "catch Block");
            e.printStackTrace();
        }
    }
}