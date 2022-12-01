package mx.ssaj.surfingattendanceapp.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import mx.ssaj.surfingattendanceapp.data.model.BioPhotos;
import mx.ssaj.surfingattendanceapp.data.repositories.BioPhotosRepository;

public class AttendanceFaceViewModel extends AndroidViewModel {

    private BioPhotosRepository bioPhotosRepository;

    public AttendanceFaceViewModel(@NonNull Application application) {
        super(application);
        bioPhotosRepository = new BioPhotosRepository(application);
    }

    public List<BioPhotos> getAllBioPhotos() {
        return bioPhotosRepository.getAllBioPhotos();
    }

}
