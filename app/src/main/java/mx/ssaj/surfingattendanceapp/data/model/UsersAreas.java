package mx.ssaj.surfingattendanceapp.data.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"user", "area"})
public class UsersAreas {

    public int user; // PK

    // Length(6)
    public String area; // PK

}
