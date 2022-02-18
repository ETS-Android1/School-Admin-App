package Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import DAO.AssessmentDAO;
import DAO.CourseDAO;
import DAO.TermDAO;
import Entity.AssessmentEntity;
import Entity.CourseEntity;
import Entity.TermEntity;

public class AppRepository {

    private final AssessmentDAO rAssessmentDAO;
    private final CourseDAO rCourseDAO;
    private final TermDAO rTermDAO;
    private List<AssessmentEntity> rAllAssessments;
    private List<CourseEntity> rAllCourses;
    private List<TermEntity> rAllTerms;

    public AppRepository(Application application) {

        AppDatabase appDB = AppDatabase.getAppDatabase(application);
        rAssessmentDAO = appDB.assessmentDAO();
        rCourseDAO = appDB.courseDAO();
        rTermDAO = appDB.termDAO();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public List<AssessmentEntity> getAllAssessments() {
        AppDatabase.databaseWriteExecutor.execute(()->{
            rAllAssessments = rAssessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rAllAssessments;
    }

    public List<CourseEntity> getAllCourses() {
        AppDatabase.databaseWriteExecutor.execute(()->{
            rAllCourses = rCourseDAO.getAllCourses();
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rAllCourses;
    }

    public List<TermEntity> getAllTerms() {
        AppDatabase.databaseWriteExecutor.execute(()->{
            rAllTerms = rTermDAO.getAllTerms();
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rAllTerms;
    }

    public void insert (AssessmentEntity assessmentEntity) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            rAssessmentDAO.insert(assessmentEntity);
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert (CourseEntity courseEntity) {
        AppDatabase.databaseWriteExecutor.execute(()->{
            rCourseDAO.insert(courseEntity);
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }

    public void insert (TermEntity termEntity) {
        AppDatabase.databaseWriteExecutor.execute(()->{
            rTermDAO.insert(termEntity);
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(AssessmentEntity assessmentEntity) {
        AppDatabase.databaseWriteExecutor.execute(()->{
            rAssessmentDAO.deleteAssessment(assessmentEntity);
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(CourseEntity courseEntity) {
        AppDatabase.databaseWriteExecutor.execute(()->{
            rCourseDAO.deleteCourse(courseEntity);
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(TermEntity termEntity) {
        AppDatabase.databaseWriteExecutor.execute(()->{
            rTermDAO.deleteTerm(termEntity);
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllTerms() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            rTermDAO.deleteAllTerms();
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
