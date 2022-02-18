package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import Database.AppRepository;
import Entity.AssessmentEntity;
import Entity.CourseEntity;

public class CourseDetailEditActivity extends AppCompatActivity {

    AppRepository appRepository;
    AssessmentActivityAdapter assessmentActivityAdapter;

    EditText courseNameEdit;
    EditText courseStartEdit;
    EditText courseEndEdit;
    EditText courseStatusEdit;
    EditText courseInstructorEdit;
    EditText coursePhoneEdit;
    EditText courseEmailEdit;
    EditText courseNotesEdit;
    int courseID;

    CourseEntity selectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail_edit_actviity);

        appRepository = new AppRepository(getApplication());

        String courseName;
        String courseStart;
        String courseEnd;
        String courseStatus;
        String courseInstructor;
        String coursePhone;
        String courseEmail;
        String courseNotes;

        courseName = getIntent().getStringExtra("courseName");
        courseStart = getIntent().getStringExtra("courseStart");
        courseEnd = getIntent().getStringExtra("courseEnd");
        courseStatus = getIntent().getStringExtra("courseStatus");
        courseInstructor = getIntent().getStringExtra("courseInstructor");
        coursePhone = getIntent().getStringExtra("coursePhone");
        courseEmail = getIntent().getStringExtra("courseEmail");
        courseNotes = getIntent().getStringExtra("courseNotes");

        selectedCourse = new CourseEntity(courseName,courseStart,courseEnd,courseStatus,courseInstructor,courseEmail,coursePhone,courseNotes, getIntent().getIntExtra("associatedTermID",-1));
        selectedCourse.setCourseID(getIntent().getIntExtra("courseID", -1));

        courseNameEdit = findViewById(R.id.tvCourseTitleEdit);
        courseStartEdit = findViewById(R.id.tvCourseStartEdit);
        courseEndEdit = findViewById(R.id.tvCourseEndEdit);
        courseStatusEdit = findViewById(R.id.tvCourseStatusEdit);
        courseInstructorEdit = findViewById(R.id.tvCourseInstructorEdit);
        coursePhoneEdit = findViewById(R.id.tvCoursePhoneEdit);
        courseEmailEdit = findViewById(R.id.tvCourseEmailEdit);
        courseNotesEdit = findViewById(R.id.tvNoteEdit);

        courseNameEdit.setText(courseName);
        courseStartEdit.setText(courseStart);
        courseEndEdit.setText(courseEnd);
        courseStatusEdit.setText(courseStatus);
        courseInstructorEdit.setText(courseInstructor);
        coursePhoneEdit.setText(coursePhone);
        courseEmailEdit.setText(courseEmail);
        courseNotesEdit.setText(courseNotes);

        RecyclerView recyclerView = findViewById(R.id.assessmentRecycler);
        assessmentActivityAdapter = new AssessmentActivityAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(assessmentActivityAdapter);
        assessmentActivityAdapter.setAssessmentList(filterAssessmentsByCourse());
    }

    public void editCourse(View view) {

        CourseEntity editedCourse;

        courseID = getIntent().getIntExtra("courseID", -1);
        String courseName = courseNameEdit.getText().toString();
        String courseStart = courseStartEdit.getText().toString();
        String courseEnd = courseEndEdit.getText().toString();
        String courseStatus = courseStatusEdit.getText().toString();
        String courseInstructor = courseInstructorEdit.getText().toString();
        String coursePhone = coursePhoneEdit.getText().toString();
        String courseEmail = courseEmailEdit.getText().toString();
        String courseNotes = courseNotesEdit.getText().toString();
        int associatedTerm = getIntent().getIntExtra("courseTermID", -1);

        editedCourse = new CourseEntity(courseName, courseStart, courseEnd, courseStatus, courseInstructor, coursePhone, courseEmail, courseNotes, associatedTerm);
        editedCourse.setCourseID(courseID);
        appRepository.insert(editedCourse);

        finish();
    }

    public void addAssessment(View view) {
        courseID = getIntent().getIntExtra("courseID", -1);
        Intent intent = new Intent(CourseDetailEditActivity.this, AssessmentAddActivity.class);
        intent.putExtra("courseID", courseID);
        startActivity(intent);
    }

    private List<AssessmentEntity> filterAssessmentsByCourse() {
        List<AssessmentEntity> filteredAssessments = new ArrayList<>();
        courseID = getIntent().getIntExtra("courseID", -1);

        if (appRepository.getAllAssessments().size() > 0) {
            for (AssessmentEntity a : appRepository.getAllAssessments()) {
                if (a.getAssociatedCourse() == courseID) {
                    filteredAssessments.add(a);
                }
            }
        }
        return filteredAssessments;

    }

    @Override
    protected void onStart() {
        super.onStart();
        assessmentActivityAdapter.setAssessmentList(filterAssessmentsByCourse());
    }

    public void deleteCourse(View view) {
        appRepository.delete(selectedCourse);
        finish();
    }

    public void setAlert(View view) {
        String courseStartDate = courseStartEdit.getText().toString();
        String courseEndDate = courseEndEdit.getText().toString();
        String courseName = courseNameEdit.getText().toString();

        if (!dateIsParsable(courseStartDate) || !dateIsParsable(courseEndDate)){
            Toast.makeText(getApplicationContext(),"A date entered is invalid, please enter in format dd/mm/yyyy", Toast.LENGTH_LONG).show();
            return;}

        callAlarm(parseDate(courseStartDate), "Start Date ", courseName);
        callAlarm(parseDate(courseEndDate), "End Date ", courseName);

        Toast.makeText(getApplicationContext(),"Alert for date: " + courseStartDate + " and " + courseEndDate + "has been set at 12:05AM ", Toast.LENGTH_LONG).show();
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

    private void callAlarm(Calendar date, String dateType, String instanceName) {
        Intent intent = new Intent(CourseDetailEditActivity.this, DateNotificationReceiver.class);

        intent.putExtra("entityType", "Course");
        intent.putExtra("dateType", dateType);
        intent.putExtra("instanceName", instanceName);
        final int id = (int)System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(CourseDetailEditActivity.this, id, intent, 0);


        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(), pendingIntent);

    }


    public void onShare(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, courseNotesEdit.getText().toString());
        sendIntent.putExtra(Intent.EXTRA_TITLE, "Sharing notes from " + courseNameEdit.getText().toString());
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
}