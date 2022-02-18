package DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import Entity.AssessmentEntity;
import Entity.TermEntity;

@Dao
public interface TermDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TermEntity termEntity);

    @Delete
    void deleteTerm(TermEntity term);

    @Query("DELETE FROM term_table")
    void deleteAllTerms();

    @Query("SELECT * FROM term_table")
    List<TermEntity> getAllTerms();



}
