package com.example.myapplication;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import android.content.Intent;
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

        // switch to start work manager
        Switch switchButton = findViewById(R.id.switch1);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.d("MyApplication Logs for services", "Switch button is on "+isChecked);
                    PeriodicWorkRequest uploadWorkRequest =
                            new PeriodicWorkRequest.Builder(UploadWorker.class, 15 , TimeUnit.MINUTES)
                                    .addTag("myPeriodicRequest").build();
                    workManger.enqueue(uploadWorkRequest);
                }else{
                    Log.d("MyApplication Logs for services", "Switch button is on "+ isChecked);
                    workManger.cancelAllWorkByTag("myPeriodicRequest");
                    stopService(new Intent(getApplicationContext(), MyForegroundService.class));
                }
            }
        });
    }
}