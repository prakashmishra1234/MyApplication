package com.example.myapplication;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

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
    }
}