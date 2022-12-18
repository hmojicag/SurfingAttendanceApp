package mx.ssaj.surfingattendanceapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import mx.ssaj.surfingattendanceapp.data.model.Users;

@Dao
public interface UsersDao {

    @Query("SELECT COUNT(*) FROM Users")
    int getUsersCount();

    @Query("SELECT * FROM Users")
    List<Users> getAllUsers();

    @Query("SELECT * FROM Users WHERE user = :userId")
    Users findById(int userId);

    @Query("SELECT * FROM Users ORDER BY user ASC")
    LiveData<List<Users>> getAllUsersLive();

    @Query("DELETE FROM Users")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Users users);

}
