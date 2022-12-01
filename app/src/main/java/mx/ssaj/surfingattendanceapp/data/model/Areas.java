package mx.ssaj.surfingattendanceapp.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Areas {

    @PrimaryKey
    // Length(6)
    public String area;

    // Length(50)
    public String description;

}
