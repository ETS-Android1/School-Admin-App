package com.example.myapplication.UI;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.myapplication.R;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.Calendar;
import java.util.TimeZone;

import Database.AppDatabase;
import Database.AppRepository;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppRepository appRepository = new AppRepository(getApplication());
        appRepository.getAllTerms();

        initNotificationChannel();


        TimeZone timeZone = TimeZone.getDefault();

        Calendar calDate = Calendar.getInstance(timeZone);
        Calendar calDate1 = Calendar.getInstance(timeZone);

        calDate.set(2021,7,28,0,0,10);
        calDate1.set(2021,7,29,0,0,10);

//        Intent intent = new Intent(MainActivity.this, DateNotificationReceiver.class);
//
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, (int)System.currentTimeMillis(), intent, 0);
//        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(MainActivity.this,(int)System.currentTimeMillis()+5000, intent,0);
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calDate.getTimeInMillis(), pendingIntent);
////        alarmManager1.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+10000, pendingIntent);
//
//        alarmManager1.set(AlarmManager.RTC_WAKEUP, calDate1.getTimeInMillis(), pendingIntent1);;
//
//        callAlarm(calDate);
//        callAlarm(calDate1);
    }



    public void coursesButton(View view) {
        Intent intent =new Intent(MainActivity.this, TermActivity.class);
        startActivity(intent);

    }

    public void termsButton(View view) {
        Intent intent = new Intent(MainActivity.this, TermActivity.class);
        startActivity(intent);
    }

    private void initNotificationChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("notifyDate", "dateReminderChannel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Date Reminder Channel");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}