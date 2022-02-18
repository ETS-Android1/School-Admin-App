package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import Database.AppRepository;
import Entity.CourseEntity;
import Entity.TermEntity;

public class TermDetailEditActivity extends AppCompatActivity {

    AppRepository appRepository;
    CourseActivityAdapter courseActivityAdapter;
    EditText termTitleEdit;
    EditText termStartEdit;
    EditText termEndEdit;
    int termID;

    TermEntity selectedTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail_edit);

        appRepository = new AppRepository(getApplication());

        String termName;
        String termStart;
        String termEnd;

        termName = getIntent().getStringExtra("termName");
        termStart = getIntent().getStringExtra("termStart");
        termEnd = getIntent().getStringExtra("termEnd");
        termID = getIntent().getIntExtra("termID", -1);

        termTitleEdit = findViewById(R.id.termTitleTextEdit);
        termStartEdit = findViewById(R.id.termStartDateTextEdit);
        termEndEdit = findViewById(R.id.termEndDateTextEdit);

        termTitleEdit.setText(termName);
        termStartEdit.setText(termStart);
        termEndEdit.setText(termEnd);

        selectedTerm = new TermEntity(termName,termStart,termEnd);
        selectedTerm.setTermID(termID);

        RecyclerView recyclerView = findViewById(R.id.courseRecycler);
        courseActivityAdapter = new CourseActivityAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(courseActivityAdapter);
        courseActivityAdapter.setCoursesList(filterCoursesByTermID());

    }

    public void editTerm(View view) {

        TermEntity editedTerm;

        String termName = termTitleEdit.getText().toString();
        String termStart = termStartEdit.getText().toString();
        String termEnd = termEndEdit.getText().toString();
        int id = getIntent().getIntExtra("termID", -1);

        editedTerm = new TermEntity(termName,termStart,termEnd);
        editedTerm.setTermID(id);

        appRepository.insert(editedTerm);

        Intent intent = new Intent(TermDetailEditActivity.this,TermActivity.class);
        startActivity(intent);

    }

    private List<CourseEntity> filterCoursesByTermID() {
        List<CourseEntity> filteredCourses = new ArrayList<>();
        if (appRepository.getAllCourses().size() > 0) {
            for (CourseEntity c : appRepository.getAllCourses()) {
                if (c.getAssociatedTermID() == termID) {
                    filteredCourses.add(c);
                }
            }
        }
        return filteredCourses;
    }

    public void addCourse(View view) {
        Intent intent = new Intent(TermDetailEditActivity.this, CourseAddActivity.class);
        intent.putExtra("termID", termID);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        courseActivityAdapter.setCoursesList(filterCoursesByTermID());
    }

    public void deleteTerm(View view) {
        if (filterCoursesByTermID().isEmpty()) {
            appRepository.delete(selectedTerm);
            finish();
        } else Toast.makeText(getApplicationContext(),"Cannot Delete Term Containing Courses", Toast.LENGTH_LONG).show();
    }
}

