package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;

import Database.AppRepository;
import Entity.AssessmentEntity;

public class AssessmentAddActivity extends AppCompatActivity {

    private AppRepository appRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appRepository = new AppRepository(getApplication());
        setContentView(R.layout.activity_assessment_add);
    }

    public void addAssessment(View view) {

        int associatedCourse = getIntent().getIntExtra("courseID", -1);

        AssessmentEntity newAssessment;
        EditText titleEdit = findViewById(R.id.tvAssessmentTitle);
        EditText typeEdit =  findViewById(R.id.tvAssessmentType);
        EditText startEdit =  findViewById(R.id.tvAssessmentStart);
        EditText endEdit =  findViewById(R.id.tvAssessmentEnd);

        String title = titleEdit.getText().toString();
        String type =  typeEdit.getText().toString();
        String start = startEdit.getText().toString();
        String end = endEdit.getText().toString();

        newAssessment = new AssessmentEntity(title,type,start,end,associatedCourse);

        appRepository.insert(newAssessment);

        finish();

    }
}