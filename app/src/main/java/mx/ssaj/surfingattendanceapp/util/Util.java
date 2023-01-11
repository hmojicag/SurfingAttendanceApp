package mx.ssaj.surfingattendanceapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String SurfingDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    public static SimpleDateFormat SurfingSimpleDateFormat = new SimpleDateFormat(SurfingDateTimeFormat);


    /**
     * Get a string representing the Date and Time NOW
     * in format "yyyy-MM-dd HH:mm:ss"
     */
    public static String getDateTimeNow() {
        return SurfingSimpleDateFormat.format(new Date());
    }

}
