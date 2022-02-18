package Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import DAO.AssessmentDAO;
import DAO.CourseDAO;
import DAO.TermDAO;
import Entity.AssessmentEntity;
import Entity.CourseEntity;
import Entity.TermEntity;

@Database(entities = {AssessmentEntity.class, CourseEntity.class, TermEntity.class}, version = 8)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AssessmentDAO assessmentDAO();
    public abstract CourseDAO courseDAO();
    public abstract TermDAO termDAO();

    private static final int THREAD_NUM = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(THREAD_NUM);


    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }



}
