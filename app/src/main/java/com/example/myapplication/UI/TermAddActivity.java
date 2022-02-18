package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;

import Database.AppRepository;
import Entity.TermEntity;

public class TermAddActivity extends AppCompatActivity {

    private AppRepository appRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appRepository = new AppRepository(getApplication());
        setContentView(R.layout.content_term_add);
    }


    public void addTerm(View view) {
        Intent intent = new Intent(TermAddActivity.this, TermActivity.class);

        TermEntity newTerm;
        EditText titleEdit = findViewById(R.id.tvCourseTitle);
        EditText termStartEdit = findViewById(R.id.tvCourseStart);
        EditText termEndEdit = findViewById(R.id.tvCourseEnd);

        String termTitle = titleEdit.getText().toString();
        String termStart = termStartEdit.getText().toString();
        String termEnd = termEndEdit.getText().toString();

        newTerm = new TermEntity(termTitle,termStart,termEnd);
        appRepository.insert(newTerm);

        setResult(2, intent);
        finish();

    }

}