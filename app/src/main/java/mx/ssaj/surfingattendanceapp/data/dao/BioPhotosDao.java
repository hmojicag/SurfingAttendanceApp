package mx.ssaj.surfingattendanceapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import mx.ssaj.surfingattendanceapp.data.model.BioPhotos;

@Dao
public interface BioPhotosDao {

    @Query("SELECT * FROM BioPhotos WHERE user = :userId AND type = :type")
    BioPhotos findById(int userId, int type);

    @Query("SELECT * FROM BioPhotos WHERE type = 9")
    List<BioPhotos> getAllBioPhotosForAttendance();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BioPhotos bioPhotos);

    @Update(entity = BioPhotos.class)
    void update(BioPhotos bioPhotos);
}
