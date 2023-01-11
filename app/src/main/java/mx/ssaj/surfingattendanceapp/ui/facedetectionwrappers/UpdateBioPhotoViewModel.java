package mx.ssaj.surfingattendanceapp.ui.facedetectionwrappers;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.apache.commons.lang3.ArrayUtils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import mx.ssaj.surfingattendanceapp.data.model.BioPhotos;
import mx.ssaj.surfingattendanceapp.data.repositories.BioPhotosRepository;
import mx.ssaj.surfingattendanceapp.detection.dto.FaceRecord;
import mx.ssaj.surfingattendanceapp.util.BioDataType;

public class UpdateBioPhotoViewModel extends AndroidViewModel {
    private static String TAG = "UpdateBioPhotoViewModel";
    private BioPhotosRepository bioPhotosRepository;

    public UpdateBioPhotoViewModel(@NonNull Application application) {
        super(application);
        bioPhotosRepository = new BioPhotosRepository(application);
    }

    public void upsertBioPhotos(int userId, Bitmap fullPhoto, FaceRecord face) {
        List<BioPhotos> bioPhotos = new ArrayList<>();

        // Full Photo to be stored in DB for SurfingAttendance and Horus
        BioPhotos biophoto = new BioPhotos();
        biophoto.user = userId;
        biophoto.type = BioDataType.BIOPHOTO_JPG.getType();
        biophoto.setBioPhotoContent(String.format("%d.jpg", userId), fullPhoto);
        bioPhotos.add(biophoto);

        // Thumbnail Photo to be stored in DB for SurfingAttendance
        BioPhotos thumbNailBiophoto = new BioPhotos();
        thumbNailBiophoto.user = userId;
        thumbNailBiophoto.type = BioDataType.BIOPHOTO_THUMBNAIL_JPG.getType();
        thumbNailBiophoto.setBioPhotoContent(String.format("%d_t.jpg", userId), face.getRecognition().getCrop());
        bioPhotos.add(thumbNailBiophoto);

        // Log extras for debugging purposes
        Log.i(TAG, "Logging extras while storing user" + userId + " to DB: " + ArrayUtils.toString((float[][]) face.getRecognition().getExtra()));

        bioPhotosRepository.upsertBioPhotos(bioPhotos);
    }
}
