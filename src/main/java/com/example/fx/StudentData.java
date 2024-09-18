package com.example.fx;

import java.util.Date;

public class StudentData {
    private int studentId;
    private String name;
    private String surname;
    private String gender;
    private Date dateOfBirth;
    private String course;
    private String country;
    private String status;

    // Default constructor
    public StudentData() {
    }

    // Parameterized constructor
    public StudentData(int studentId, String name, String surname, String gender, Date dateOfBirth, String course, String country, String status) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.course = course;
        this.country = country;
        this.status = status;
    }

    // Getters and setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
