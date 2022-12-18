package mx.ssaj.surfingattendanceapp.data.dto;

import java.util.Date;

import mx.ssaj.surfingattendanceapp.detection.dto.FaceRecord;

public class AttendanceRecord {

    private Date date;

    private FaceRecord faceRecord;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FaceRecord getFaceRecord() {
        return faceRecord;
    }

    public void setFaceRecord(FaceRecord faceRecord) {
        this.faceRecord = faceRecord;
    }
}
