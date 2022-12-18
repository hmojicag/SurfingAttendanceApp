package mx.ssaj.surfingattendanceapp.ui.facedetectionwrappers;

import android.graphics.Bitmap;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ssaj.surfingattendanceapp.data.dto.AttendanceRecord;
import mx.ssaj.surfingattendanceapp.detection.DetectorActivity;
import mx.ssaj.surfingattendanceapp.detection.dto.FaceRecord;

public class AttendanceRecordsByFaceDetectionActivity extends DetectorActivity {

    private static List<FaceRecord> faceRegistry = new ArrayList<>();
    private static List<AttendanceRecord> attendanceRecords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide controls that won't be needed
        showAddButton(true);
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
    protected void onFaceFeaturesDetected(FaceRecord face) {
        faceRegistry.add(face);
        registerNewFace(face);
    }

    @Override
    protected List<FaceRecord> initializeFaceRegistry() {
        // Recover all faces from DB and register for detection
        return faceRegistry;
    }

}
