package mx.ssaj.surfingattendanceapp.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import mx.ssaj.surfingattendanceapp.data.SurfingAttendanceDatabase;
import mx.ssaj.surfingattendanceapp.data.dao.UsersDao;
import mx.ssaj.surfingattendanceapp.data.model.Users;

public class UsersRepository {

    private UsersDao usersDao;

    public UsersRepository(Application application) {
        SurfingAttendanceDatabase surfingAttendanceDatabase = SurfingAttendanceDatabase.getDatabase(application);
        usersDao = surfingAttendanceDatabase.usersDao();
    }

    public List<Users> getAllUsers() {
        return usersDao.getAllUsers();
    }

    public Users findById(int userId) {
        return usersDao.findById(userId);
    }

    public int nextId() {
        int userId = usersDao.nextId();
        userId = userId == 0 ? 1 : userId;
        return userId;
    }

    public LiveData<List<Users>> getAllUsersLive() {
        return usersDao.getAllUsersLive();
    }

    public void insert(Users user) {
        usersDao.insert(user);
    }

    public void update(Users user) {
        usersDao.update(user);
    }
}
