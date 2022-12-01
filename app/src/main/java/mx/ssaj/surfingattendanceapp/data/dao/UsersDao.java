package mx.ssaj.surfingattendanceapp.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import mx.ssaj.surfingattendanceapp.data.model.Users;

@Dao
public interface UsersDao {

    @Query("SELECT * from Users")
    List<Users> getAllUsers();

    @Query("SELECT * from Users WHERE user = :userId")
    Users findById(int userId);

}
