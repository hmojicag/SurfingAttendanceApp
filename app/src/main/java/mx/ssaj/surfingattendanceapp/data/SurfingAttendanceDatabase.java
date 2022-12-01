package mx.ssaj.surfingattendanceapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mx.ssaj.surfingattendanceapp.data.dao.BioPhotosDao;
import mx.ssaj.surfingattendanceapp.data.dao.UsersDao;
import mx.ssaj.surfingattendanceapp.data.model.Areas;
import mx.ssaj.surfingattendanceapp.data.model.BioPhotos;
import mx.ssaj.surfingattendanceapp.data.model.Users;
import mx.ssaj.surfingattendanceapp.data.model.UsersAreas;

@Database(entities = {
        Areas.class,
        BioPhotos.class,
        Users.class,
        UsersAreas.class
}, version = 1, exportSchema = false)
public abstract class SurfingAttendanceDatabase extends RoomDatabase {
    public abstract BioPhotosDao bioPhotosDao();
    public abstract UsersDao usersDao();

    private static volatile SurfingAttendanceDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SurfingAttendanceDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SurfingAttendanceDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    SurfingAttendanceDatabase.class, "surfing_attendance_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
