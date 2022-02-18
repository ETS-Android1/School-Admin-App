package DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import Entity.AssessmentEntity;
import Entity.CourseEntity;

@Dao
public interface CourseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CourseEntity courseEntity);

    @Delete
    void deleteCourse(CourseEntity courseEntity);

    @Query("SELECT * FROM course_table ORDER BY courseID ASC")
    List<CourseEntity> getAllCourses();


}
