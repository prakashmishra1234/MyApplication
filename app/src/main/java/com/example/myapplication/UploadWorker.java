package com.example.myapplication;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import android.os.Build;
import android.util.Log;
import java.util.List;

public class UploadWorker extends Worker {

    public UploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        //specify your work here
        try{
            Log.d("MyApplication Logs for services", "Work manager is running successfully");

            //Checking foreground service running status
            boolean IsForegroundServiceRunning = isForegroundServiceRunning(getApplicationContext());
            Log.d("MyApplication Logs for services", "is foreground service running "+ Boolean.toString(IsForegroundServiceRunning));

            // Starting Foreground Service if not running
            if(!IsForegroundServiceRunning){
//                Intent serviceIntent = new Intent(getApplicationContext(), MyForegroundService.class);
//                ContextCompat.startForegroundService(getApplicationContext(), serviceIntent);
                startForegroundService();
            }
            return Result.success();
        }catch (Exception exception){
            Log.d("MyApplication Logs for services", "Exception do work "+ exception.toString());
            return Result.failure();
        }
    }

    // Method for checking foreground service running status
    private boolean isForegroundServiceRunning(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            List<ActivityManager.RunningServiceInfo> runningServices = manager.getRunningServices(Integer.MAX_VALUE);
            for (ActivityManager.RunningServiceInfo service : runningServices) {
                if (MyForegroundService.class.getName().equals(service.service.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    // method to start foreground service
    private void startForegroundService (){
        Context context = getApplicationContext();
        Intent serviceIntent = new Intent(getApplicationContext(), MyForegroundService.class);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Log.d("MyApplication Logs for services", "Foreground service started by start foreground service");
            context.startForegroundService(serviceIntent);
        }else{
            Log.d("MyApplication Logs for services", "Foreground service started by start service");
            context.startService(serviceIntent);
        }
    }
}
