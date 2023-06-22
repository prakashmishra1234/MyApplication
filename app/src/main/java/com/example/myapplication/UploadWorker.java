package com.example.myapplication;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import android.os.Handler;
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
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {

                    Log.d("Work manager Status", "Work manager is running successfully");

                    //Checking foreground service running status
                    boolean IsForegroundServiceRunning = isForegroundServiceRunning(getApplicationContext());
                    Log.d("foreground Service Status", Boolean.toString(IsForegroundServiceRunning));

                    // Starting Foreground Service if not running
                    if(!IsForegroundServiceRunning){
                        Intent serviceIntent = new Intent(getApplicationContext(), MyForegroundService.class);
                        ContextCompat.startForegroundService(getApplicationContext(), serviceIntent);
                    }
                }
            });
            return Result.success();
        }catch (Exception io){
            Log.d("work", "doWork" + "working has not started");
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
}
