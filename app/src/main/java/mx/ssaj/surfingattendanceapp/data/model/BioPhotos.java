package mx.ssaj.surfingattendanceapp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;

@Entity(primaryKeys = {"user", "no"})
public class BioPhotos {

    @NonNull
    public int user;// PK

    @NonNull
    // A consecutive from 1 to n in order of importance
    // A user can have as many BioPhotos as needed
    public int no;// PK

    /**Name of user photo*/
    // Length(50)
    public String photoIdName;

    /**The size of user photo data in Base64 format*/
    public int photoIdSize;

    /**User photo data in Base64 format*/
    // Length(max)
    public String photoIdContent;

    /** JSON format of the set of features for this BioPhoto
     *  Example: [123.2, 9812.0, 1121.2, ...]
     * **/
    // Length(max)
    public String features;

    /** ---------------------------------------------------------------------------------------- **/
    /** Useful calculated fields  **/

    @Ignore
    private float[] feature;

    @Ignore
    public float[] getFeature() {
        if (feature == null) {
            if (StringUtils.isEmpty(features)) {
                feature = new float[]{};
            } else {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    feature = objectMapper.readValue(features, float[].class);
                } catch (Exception ex) {
                    // TODO: How to Logging???
                    ex.printStackTrace();
                    feature = new float[]{};
                }
            }
        }

        return feature;
    }

    @Ignore
    public Users User;

}
