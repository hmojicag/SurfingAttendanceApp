package mx.ssaj.surfingattendanceapp.data.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;

@Entity(primaryKeys = {"user", "type"})
public class BioPhotoFeatures {
    private static String TAG = "BioPhotoFeatures";

    @NonNull
    public int user;// PK

    @NonNull
    // Type of BioPhoto record
    public int type;// PK

    /** JSON format of the set of features for this BioPhoto which is a float[][]
     *  Example: [[123.2, 9812.0, 1121.2, ...], [...], [...]]
     * **/
    // Length(max)
    public String features;

    /** ---------------------------------------------------------------------------------------- **/
    /** Useful calculated fields and methods **/

    @Ignore
    private float[][] feature;

    @Ignore
    public float[][] getFeature() {
        if (feature == null) {
            if (StringUtils.isEmpty(features)) {
                feature = new float[][]{};
            } else {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    feature = objectMapper.readValue(features, float[][].class);
                } catch (Exception ex) {
                    Log.e(TAG, "Error while deserializing features", ex);
                    feature = new float[][]{};
                }
            }
        }

        return feature;
    }

}
