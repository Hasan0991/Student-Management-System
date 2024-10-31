package com.example.fx;

public class Subject {
    private int subjectId;
    private String subjectName;
    private int courseId;

    public Subject(int subjectId, String subjectName, int courseId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.courseId = courseId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
