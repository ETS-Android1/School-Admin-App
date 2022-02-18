package com.example.myapplication.UI;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import Database.AppDatabase;
import Database.AppRepository;
import Entity.TermEntity;

public class TermViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private AppRepository appRepository;

    private final MutableLiveData<List<TermEntity>> allTermsObserver;


    public TermViewModel(@NonNull Application application) {
        super(application);
        allTermsObserver = new MutableLiveData<>();
        appDatabase = AppDatabase.getAppDatabase(getApplication().getApplicationContext());
        appRepository = new AppRepository(getApplication());

    }

    public LiveData<List<TermEntity>> getAllTermsObserver() {
        return allTermsObserver;
    }

    public void getAllTerms() {
        List<TermEntity> terms = appRepository.getAllTerms();
       if (terms.size() > 0) {
            allTermsObserver.postValue(terms);
        } else allTermsObserver.postValue(null);
    }

    public void insert(TermEntity termEntity) {
        appRepository.insert(termEntity);
        getAllTerms();
    }


}
