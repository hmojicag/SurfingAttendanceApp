package mx.ssaj.surfingattendanceapp.detection.dto;

import android.graphics.Bitmap;

import com.google.mlkit.vision.face.Face;

import mx.ssaj.surfingattendanceapp.detection.tflite.SimilarityClassifier;

public class FaceRecord {

    private String name;

    private Face face;

    private Bitmap faceImage;

    private Bitmap faceCropped;

    private Float confidence;

    private SimilarityClassifier.Recognition recognition;

    public FaceRecord() {
    }

    public FaceRecord(Face face, Bitmap faceImage) {
        this.face = face;
        this.faceImage = faceImage;
    }

    public FaceRecord(String name, Face face, Bitmap faceImage, Bitmap faceCropped, Float confidence) {
        this.name = name;
        this.face = face;
        this.faceImage = faceImage;
        this.faceCropped = faceCropped;
        this.confidence = confidence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public Bitmap getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(Bitmap faceImage) {
        this.faceImage = faceImage;
    }

    public Bitmap getFaceCropped() {
        return faceCropped;
    }

    public void setFaceCropped(Bitmap faceCropped) {
        this.faceCropped = faceCropped;
    }

    public Float getConfidence() {
        return confidence;
    }

    public void setConfidence(Float confidence) {
        this.confidence = confidence;
    }

    public SimilarityClassifier.Recognition getRecognition() {
        return recognition;
    }

    public void setRecognition(SimilarityClassifier.Recognition recognition) {
        this.recognition = recognition;
    }

    @Override
    public String toString() {
        return "FaceRecognized{" +
                "name='" + name + '\'' +
                ", face=" + face +
                ", faceImage=" + faceImage +
                ", faceCropped=" + faceCropped +
                ", confidence=" + confidence +
                '}';
    }
}
