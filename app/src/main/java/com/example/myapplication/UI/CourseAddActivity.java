package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;

import Database.AppRepository;
import Entity.CourseEntity;

public class CourseAddActivity extends AppCompatActivity {

    private AppRepository appRepository;
    private int associatedTermID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appRepository = new AppRepository(getApplication());
        associatedTermID = getIntent().getIntExtra("termID", -1);
        setContentView(R.layout.course_add);
    }

    public void saveCourseToTerm(View view) {
//        Intent intent = new Intent(CourseAddActivity.this, TermDetailEditActivity.class);

        CourseEntity newCourse;
        EditText courseTitleEntry = findViewById(R.id.tvCourseTitle);
        EditText courseStartEntry = findViewById(R.id.tvCourseStart);
        EditText courseEndEntry = findViewById(R.id.tvCourseEnd);
        EditText courseStatusEntry = findViewById(R.id.tvCourseStatus);
        EditText courseInstructorNameEntry = findViewById(R.id.tvCourseInstructor);
        EditText courseEmailEntry = findViewById(R.id.tvCourseEmail);
        EditText coursePhoneEntry = findViewById(R.id.tvCoursePhone);
        EditText courseNotesEntry = findViewById(R.id.tvCourseNote);

        String courseName = courseTitleEntry.getText().toString();
        String courseStart = courseStartEntry.getText().toString();
        String courseEnd = courseEndEntry.getText().toString();
        String courseStatus = courseStatusEntry.getText().toString();
        String courseInstructor = courseInstructorNameEntry.getText().toString();
        String courseEmail = courseEmailEntry.getText().toString();
        String coursePhone = coursePhoneEntry.getText().toString();
        String courseNote = courseNotesEntry.getText().toString();

        newCourse = new CourseEntity(courseName,courseStart,courseEnd,courseStatus, courseInstructor, courseEmail, coursePhone, courseNote, associatedTermID);
        appRepository.insert(newCourse);

//        startActivity(intent);
        finish();

    }
}