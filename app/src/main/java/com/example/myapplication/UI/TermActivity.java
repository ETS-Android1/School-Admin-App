package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

import Database.AppRepository;

public class TermActivity extends AppCompatActivity {

    private AppRepository appRepository;
    private TermViewModel termViewModel;
    public TermActivityAdapter termActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_activity);
        appRepository = new AppRepository(getApplication());
        appRepository.getAllTerms();
        RecyclerView recyclerView = findViewById(R.id.term_recycler_view);



        termActivityAdapter = new TermActivityAdapter(this);
        termActivityAdapter.setTermList(appRepository.getAllTerms());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(termActivityAdapter);
        termActivityAdapter.setTermList(appRepository.getAllTerms());

//
//        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);
//        termViewModel.getAllTermsObserver().observe(this, new Observer<List<TermEntity>>() {
//            @Override
//            public void onChanged(List<TermEntity> termEntities) {
//                if (termEntities != null) {
//                    termActivityAdapter.setTermList(termEntities);
//                }
//            }
//        });

    }

    public void addTerm (View view) {
        Intent intent = new Intent(TermActivity.this, TermAddActivity.class);
        startActivityForResult(intent, 2);   ;

    }

    @Override
        protected void onActivityResult(int requestCode, int resultCode,Intent data) {
            super .onActivityResult(requestCode, resultCode, data);
            if (requestCode == 2) {
                if (appRepository.getAllTerms().size() > 0) {
                    termActivityAdapter.notifyItemInserted(appRepository.getAllTerms().size()-1);
                }
            }
    }

    @Override
    protected void onStart() {
        super.onStart();
        termActivityAdapter.setTermList(appRepository.getAllTerms());
    }
}