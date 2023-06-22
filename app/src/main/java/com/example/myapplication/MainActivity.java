package com.example.myapplication;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

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

        // switch to start work manager
        Switch switchButton = findViewById(R.id.switch1);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.d("switchButton", "Switch button "+isChecked);
                    workManger.enqueue(uploadWorkRequest);
                }else{
                    Log.d("switchButton", "onCheckedChanged: "+ workManger);
                    workManger.cancelAllWork();
                    Log.d("switchButton", "Switch button "+isChecked);
                }
            }
        });
    }
}