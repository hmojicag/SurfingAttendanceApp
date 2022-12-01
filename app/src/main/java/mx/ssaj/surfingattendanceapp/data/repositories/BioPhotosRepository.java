package mx.ssaj.surfingattendanceapp.data.repositories;

import android.app.Application;

import java.util.List;

import mx.ssaj.surfingattendanceapp.data.SurfingAttendanceDatabase;
import mx.ssaj.surfingattendanceapp.data.dao.BioPhotosDao;
import mx.ssaj.surfingattendanceapp.data.dao.UsersDao;
import mx.ssaj.surfingattendanceapp.data.model.BioPhotos;

public class BioPhotosRepository {

    private BioPhotosDao bioPhotosDao;
    private UsersDao usersDao;

    public BioPhotosRepository(Application application) {
        SurfingAttendanceDatabase surfingAttendanceDatabase = SurfingAttendanceDatabase.getDatabase(application);
        bioPhotosDao = surfingAttendanceDatabase.bioPhotosDao();
        usersDao = surfingAttendanceDatabase.usersDao();
    }

    public List<BioPhotos> getAllBioPhotos() {
        List<BioPhotos> bioPhotos = bioPhotosDao.getAllBioPhotos();
        for (BioPhotos bioPhoto: bioPhotos) {
            bioPhoto.User = usersDao.findById(bioPhoto.user);
            bioPhoto.getFeature();// Call once to initialize
        }
        return bioPhotos;
    }

}
