package com.a7.types;

public class MyCourseProgress {
    private String courseName;
    private String lecturerName;
    private String percentage;
    
    public MyCourseProgress(String courseName, String lecturerName, String percentage) {
        this.courseName = courseName;
        this.lecturerName = lecturerName;
        this.percentage = percentage;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    
}
