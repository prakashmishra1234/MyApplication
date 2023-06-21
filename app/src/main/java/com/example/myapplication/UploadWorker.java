package com.example.myapplication;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.work.Logger;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

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
                    Log.d("Working WM", "Work manager 2 is running...");
                    Toast.makeText(getApplicationContext(), "Work is being done here in background thread", Toast.LENGTH_SHORT).show();
                }
            });
            return Result.success();
//            return Result.retry();
        }catch (Exception io){
            Log.d("work", "doWork" + "working has not started");
            return Result.failure();
        }
    }
}
