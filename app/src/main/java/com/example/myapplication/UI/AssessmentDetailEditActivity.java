package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.Calendar;
import java.util.TimeZone;

import Database.AppRepository;
import Entity.AssessmentEntity;

public class AssessmentDetailEditActivity extends AppCompatActivity {

    AppRepository appRepository;

    EditText assessTitleEdit;
    EditText assessTypeEdit;
    EditText assessStartEdit;
    EditText assessEndEdit;
    int courseAssessID;

    AssessmentEntity selectedAssessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail_edit);

        appRepository = new AppRepository(getApplication());

        String assessTitle;
        String assessType;
        String assessStart;
        String assessEnd;

        assessTitle = getIntent().getStringExtra("assessTitle");
        assessType = getIntent().getStringExtra("assessType");
        assessStart = getIntent().getStringExtra("assessStart");
        assessEnd = getIntent().getStringExtra("assessEnd");

        selectedAssessment = new AssessmentEntity(assessTitle,assessType,assessStart,assessEnd, getIntent().getIntExtra("assessCourseID", -1));
        selectedAssessment.setAssessmentID(getIntent().getIntExtra("assessID",-1));

        assessTitleEdit = findViewById(R.id.tvAssessmentTitleEdit);
        assessTypeEdit = findViewById(R.id.tvAssessmentTypeEdit);
        assessStartEdit = findViewById(R.id.tvAssessmentStartEdit);
        assessEndEdit = findViewById(R.id.tvAssessmentEndEdit);

        assessTitleEdit.setText(assessTitle);
        assessTypeEdit.setText(assessType);
        assessStartEdit.setText(assessStart);
        assessEndEdit.setText(assessEnd);

    }

    public void editAssessment(View view) {

        AssessmentEntity editedAssessment;

        courseAssessID = getIntent().getIntExtra("assessCourseID",-1);
        String assessName = assessTitleEdit.getText().toString();
        String assessType = assessTypeEdit.getText().toString();
        String assessStart = assessStartEdit.getText().toString();
        String assessEnd = assessEndEdit.getText().toString();
        int assessID = getIntent().getIntExtra("assessID", -1);

        editedAssessment = new AssessmentEntity(assessName,assessType,assessStart,assessEnd,courseAssessID);
        editedAssessment.setAssessmentID(assessID);

        appRepository.insert(editedAssessment);

        finish();
    }

    public void deleteAssessment(View view) {
        appRepository.delete(selectedAssessment);
        finish();
    }

    private Calendar parseDate(String date) {

        TimeZone timeZone = TimeZone.getDefault();

        Calendar calDate = Calendar.getInstance(timeZone);


        String[] monthDayYear = date.split("/");
        int month = Integer.parseInt(monthDayYear[0]) - 1;
        int day = Integer.parseInt(monthDayYear[1]);
        int year = Integer.parseInt(monthDayYear[2]);

        calDate.set(year,month,day,0,5,5);

        return calDate;
    }

    private boolean dateIsParsable(String date) {

        if (date.isEmpty()) {return false;}

        String[] monthDayYear = date.split("/");

        if (monthDayYear.length != 3) {return false;}

        String month = monthDayYear[0];
        String day = monthDayYear[1];
        String year = monthDayYear[2];

        int monthInt;
        int dayInt;
        int yearInt;

        try {
            monthInt = Integer.parseInt(month);
            dayInt = Integer.parseInt(day);
            yearInt = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        if (monthInt < 0 || monthInt > 12 || dayInt < 0 || dayInt > 31 || yearInt < 2010 || yearInt > 2040) {return false;}

        return true;

    }

    private void callAlarm(Calendar date, String dateType, String instanceName) {
        Intent intent = new Intent(AssessmentDetailEditActivity.this, DateNotificationReceiver.class);

        intent.putExtra("entityType", "Assessment");
        intent.putExtra("dateType", dateType);
        intent.putExtra("instanceName", instanceName);
        final int id = (int)System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(AssessmentDetailEditActivity.this, id, intent, 0);


        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(), pendingIntent);

    }

    public void setAlert(View view) {
        String startDate = assessStartEdit.getText().toString();
        String endDate = assessEndEdit.getText().toString();
        String assessmentName = assessTitleEdit.getText().toString();

        if (!dateIsParsable(startDate) || !dateIsParsable(endDate)){
            Toast.makeText(getApplicationContext(),"A date entered is invalid, please enter in format dd/mm/yyyy", Toast.LENGTH_LONG).show();
            return;}

        callAlarm(parseDate(startDate), "Start Date", assessmentName);
        callAlarm(parseDate(endDate), "End Date", assessmentName);

        Toast.makeText(getApplicationContext(),"Alert for date: " + startDate + " and " + endDate + " has been set at 12:05AM ", Toast.LENGTH_LONG).show();
    }
}