package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Work Manager
        WorkManager workManger =  WorkManager.getInstance(MainActivity.this);
        PeriodicWorkRequest uploadWorkRequest =
             new PeriodicWorkRequest.Builder(UploadWorker.class, 15 , TimeUnit.MINUTES).build();
        workManger.enqueue(uploadWorkRequest);


        // Foreground Service
//        Intent serviceIntent = new Intent(this, MyForegroundService.class);
//        startForegroundService(serviceIntent);
//        Log.d("runningStatus", Boolean.toString(foregroundServiceRunning()));
//        foregroundServiceRunning();

    }

    public boolean foregroundServiceRunning(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service: activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if(MyForegroundService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}