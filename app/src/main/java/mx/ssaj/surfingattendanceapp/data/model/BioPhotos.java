package mx.ssaj.surfingattendanceapp.data.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Date;

@Entity(primaryKeys = {"user", "type"})
public class BioPhotos {

    @NonNull
    public int user;// PK

    @NonNull
    // Type of BioPhoto record
    public int type;// PK

    /** BioPhoto content **/
    /** ------------------------------------------------------------------------------------------*/
    /**Name of user photo*/
    // Length(50)
    public String photoIdName;

    /**The size of user photo data in Base64 format*/
    public int photoIdSize;

    /**User photo data in Base64 format*/
    // Length(max)
    public String photoIdContent;

    /** ------------------------------------------------------------------------------------------*/

    // Date format: "yyyy-MM-dd HH:mm:ss"
    public String lastUpdated;

    // Boolean. Is this record synced to SurfingTime already?
    public int isSync;

    /** ---------------------------------------------------------------------------------------- **/
    /** Useful calculated fields and methods **/

    @Ignore
    public Bitmap getPhoto() {
        byte[] bitmapdata = Base64.getDecoder().decode(photoIdContent);
        return BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
    }

    @Ignore
    public void setBioPhotoContent(String name, Bitmap bitmap) {
        // Compressing Bitmap into a PNG, extract its file bytes
        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, blob);
        byte[] bitmapdata = blob.toByteArray();
        // Encode JPG file bytes to Base64
        photoIdName = name;
        photoIdContent = Base64.getEncoder().encodeToString(bitmapdata);
        photoIdSize = photoIdContent.length();
    }

    public String getBioPhotoStringIdentifier() {
        if (User != null) {
            return user + "-" + User.name;
        }
        return Integer.toString(user);
    }

    @Ignore
    public Users User;

}
