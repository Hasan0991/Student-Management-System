package com.example.fx;

public class Course {
    private int courseId;
    private String courseName;
    private int courseCredits;


    public Course(int courseId, String courseName, int courseCredits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCredits = courseCredits;
    }
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseCredits() {
        return courseCredits;
    }
}
