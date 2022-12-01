package mx.ssaj.surfingattendanceapp.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import mx.ssaj.surfingattendanceapp.data.model.BioPhotos;

@Dao
public interface BioPhotosDao {

    @Query("SELECT * FROM BioPhotos")
    List<BioPhotos> getAllBioPhotos();

}
