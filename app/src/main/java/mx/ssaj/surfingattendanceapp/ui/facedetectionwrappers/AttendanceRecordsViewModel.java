package mx.ssaj.surfingattendanceapp.ui.facedetectionwrappers;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

import mx.ssaj.surfingattendanceapp.data.model.BioPhotos;
import mx.ssaj.surfingattendanceapp.data.repositories.BioPhotosRepository;
import mx.ssaj.surfingattendanceapp.detection.dto.FaceRecord;
import mx.ssaj.surfingattendanceapp.detectionwrappers.FaceDetectionSynchronousService;

public class AttendanceRecordsViewModel extends AndroidViewModel {
    private static String TAG = "AttendanceRecordsViewModel";
    private BioPhotosRepository bioPhotosRepository;
    private FaceDetectionSynchronousService faceDetectionSynchronousService;

    public AttendanceRecordsViewModel(@NonNull Application application) throws Exception {
        super(application);
        bioPhotosRepository = new BioPhotosRepository(application);
        faceDetectionSynchronousService = new FaceDetectionSynchronousService(application.getApplicationContext());
    }

    public List<FaceRecord> getFaceRegistry() {
        List<BioPhotos> bioPhotos = bioPhotosRepository.getAllBioPhotosForAttendance();
        List<FaceRecord> faceRecords = new ArrayList<>();
        for (BioPhotos bioPhoto: bioPhotos) {
            FaceRecord faceRecord = faceDetectionSynchronousService.extractFaceFeaturesFromBioPhoto(bioPhoto);
            faceRecords.add(faceRecord);
        }
        return faceRecords;
    }

}
