package mx.ssaj.surfingattendanceapp.data.repositories;

import android.app.Application;
import android.graphics.Bitmap;

import java.util.List;

import mx.ssaj.surfingattendanceapp.data.SurfingAttendanceDatabase;
import mx.ssaj.surfingattendanceapp.data.dao.BioPhotosDao;
import mx.ssaj.surfingattendanceapp.data.dao.UsersDao;
import mx.ssaj.surfingattendanceapp.data.model.BioPhotos;
import mx.ssaj.surfingattendanceapp.detection.dto.FaceRecord;
import mx.ssaj.surfingattendanceapp.util.Literals;
import mx.ssaj.surfingattendanceapp.util.Util;

public class BioPhotosRepository {

    private BioPhotosDao bioPhotosDao;
    private UsersDao usersDao;

    public BioPhotosRepository(Application application) {
        SurfingAttendanceDatabase surfingAttendanceDatabase = SurfingAttendanceDatabase.getDatabase(application);
        bioPhotosDao = surfingAttendanceDatabase.bioPhotosDao();
        usersDao = surfingAttendanceDatabase.usersDao();
    }

    public List<BioPhotos> getAllBioPhotosForAttendance() {
        List<BioPhotos> bioPhotos = bioPhotosDao.getAllBioPhotosForAttendance();
        for(BioPhotos bioPhoto: bioPhotos) {
            bioPhoto.User = usersDao.findById(bioPhoto.user);
        }
        return bioPhotos;
    }

    public void upsertBioPhotos(List<BioPhotos> bioPhotos) {
        for(BioPhotos bioPhoto: bioPhotos) {
            BioPhotos bioPhotoBd = bioPhotosDao.findById(bioPhoto.user, bioPhoto.type);
            if (bioPhotoBd == null) {// Insert new
                bioPhoto.lastUpdated = Util.getDateTimeNow();
                bioPhoto.isSync = Literals.FALSE;
                bioPhotosDao.insert(bioPhoto);
            } else {// Set editable fields and update record
                bioPhotoBd.photoIdName = bioPhoto.photoIdName;
                bioPhotoBd.photoIdSize = bioPhoto.photoIdSize;
                bioPhotoBd.photoIdContent = bioPhoto.photoIdContent;
                bioPhotoBd.lastUpdated = Util.getDateTimeNow();
                bioPhotoBd.isSync = Literals.FALSE;
                bioPhotosDao.update(bioPhotoBd);
            }
        }
    }

}
