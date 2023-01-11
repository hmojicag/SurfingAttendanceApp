package mx.ssaj.surfingattendanceapp.ui.facedetectionwrappers;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.UUID;

import mx.ssaj.surfingattendanceapp.R;
import mx.ssaj.surfingattendanceapp.data.SurfingAttendanceDatabase;
import mx.ssaj.surfingattendanceapp.detection.DetectorActivity;
import mx.ssaj.surfingattendanceapp.detection.dto.FaceRecord;
import mx.ssaj.surfingattendanceapp.ui.users.UsersFragmentDirections;

public class UpdateBioPhotoFaceDetectionActivity extends DetectorActivity {
    private int userId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide controls that won't be needed
        showAddButton(false);
        showBottomSheet(false);

        // Activate to Add New
        activateFaceFeaturesDetection(true);

        // Capture safe args
        userId = UpdateBioPhotoFaceDetectionActivityArgs.fromBundle(getIntent().getExtras()).getUserId();
    }

    @Override
    protected void onFaceFeaturesDetected(Bitmap fullPhoto, FaceRecord face) {
        face.setName(UUID.randomUUID().toString());
        //registerNewFace(face);

        // TODO: Upsert BioPhoto in database
        if (userId > -1) {// Upsert BioPhoto in database
            UpdateBioPhotoViewModel updateBioPhotoViewModel = new ViewModelProvider(this).get(UpdateBioPhotoViewModel.class);
            // Store face.getRecognition().getExtra() which is a float[][], store as a json, this is used for the recognition engine
            // Store face.getRecognition().getCrop() which is a BitMap of the cropped face that we can use as thumbnail for the BioPhoto
            // Store fullPhoto, check if can be converted to jpg for compatibility with Horus
            // TODO: DON'T STORE EXTRAS FOR NOW, JUST STORE THE FULL PHOTO AND RE-BUILD THE EXTRAS FROM IT AT APP STARTUP TO TEST WHAT'S BELOW!!!
            // TODO: check if we can take the fullPhoto in jpg and feed it to the faceRecognition engine to extract the extras
            // TODO: This way we won't need to send the extras to SurfingTime, just the fullphoto
            // TODO: And everytime a the SurfingAttendance app receives a fullPhoto from SurfingTime it will compute the extras in realTime
            SurfingAttendanceDatabase.databaseWriteExecutor.execute(() -> {
                updateBioPhotoViewModel.upsertBioPhotos(userId, fullPhoto, face);

                // TODO: Re-compute extras again using JPG PHOTO and call registerNewFace()

            });
        }

        // NAVIGATE BACK To Users Upsert Fragment
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            runOnUiThread(() -> {
                super.onBackPressed();
            });
        }
    }

}
