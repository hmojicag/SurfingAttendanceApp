package mx.ssaj.surfingattendanceapp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Users {

    // User ID number
    // user == Empleado in SurfingTime
    @NonNull
    @PrimaryKey
    public int user; // PK

    /**User privilege*/
    // 0 - Normal User
    // 1 - Admin User
    public int privilege;

    /**User name*/
    // Length(100)
    public String name;

    // 'A' Active           - Able to punch
    // 'B' Inactive         - Not able to punch
    // status == Estatus in SurfingTime
    public String status;

    /**User password*/
    // Length(16)
    public String password;

    /**Main card number*/
    // NFC Card Number
    public String mainCard;

    /**Name of user photo*/
    // Length(50)
    public String photoIdName;

    /**The size of user photo data in Base64 format*/
    public int photoIdSize;

    /**User photo data in Base64 format*/
    public String photoIdContent;

    /** ---------------------------------------------------------------------------------------- **/
    /** Useful calculated/related fields  **/

    @Ignore
    public List<Areas> Areas;

    @Ignore
    public List<BioPhotos> BioPhotos;

}
