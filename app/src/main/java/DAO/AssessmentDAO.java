package DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import Entity.AssessmentEntity;

@Dao
public interface AssessmentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AssessmentEntity assessmentEntity);

    @Delete
    void deleteAssessment(AssessmentEntity assessmentEntity);

    @Query("SELECT * FROM assessment_table ORDER BY assessmentID ASC")
    List<AssessmentEntity> getAllAssessments();


}
