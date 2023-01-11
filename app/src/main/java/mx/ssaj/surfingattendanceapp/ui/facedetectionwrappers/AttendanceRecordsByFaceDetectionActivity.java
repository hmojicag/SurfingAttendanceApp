package mx.ssaj.surfingattendanceapp.ui.facedetectionwrappers;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ssaj.surfingattendanceapp.data.SurfingAttendanceDatabase;
import mx.ssaj.surfingattendanceapp.data.dto.AttendanceRecord;
import mx.ssaj.surfingattendanceapp.detection.DetectorActivity;
import mx.ssaj.surfingattendanceapp.detection.dto.FaceRecord;

public class AttendanceRecordsByFaceDetectionActivity extends DetectorActivity {

    private List<FaceRecord> faceRegistry = new ArrayList<>();
    private List<AttendanceRecord> attendanceRecords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide controls that won't be needed
        showAddButton(false);
        showBottomSheet(false);
    }

    @Override
    protected void onFacesRecognized(Bitmap fullPhoto, List<FaceRecord> recognitions) {
        for(FaceRecord faceRecord: recognitions) {
            AttendanceRecord attendanceRecord = new AttendanceRecord();
            attendanceRecord.setDate(new Date());
            attendanceRecord.setFaceRecord(faceRecord);
            attendanceRecords.add(attendanceRecord);
        }
    }

    @Override
    protected void onFaceFeaturesDetected(Bitmap fullPhoto, FaceRecord face) {
        faceRegistry.add(face);
        registerNewFace(face);
    }

    @Override
    protected List<FaceRecord> initializeFaceRegistry() {
        // Build Face Registry from DB
        AttendanceRecordsViewModel attendanceRecordsViewModel = new ViewModelProvider(this).get(AttendanceRecordsViewModel.class);
        SurfingAttendanceDatabase.databaseWriteExecutor.execute(() -> {
            // Recover all faces from DB and register for detection
            faceRegistry = attendanceRecordsViewModel.getFaceRegistry();
            for(FaceRecord faceRecord: faceRegistry) {
                registerNewFace(faceRecord);
            }
        });

        // Return an empty array for now, while faces are fetched from DB
        return new ArrayList<>();
    }

}
