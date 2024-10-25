package com.example.fx;

import java.time.LocalDate;
import java.util.Random;

public class StudentData {
    private int studentId;
    private String name;
    private String surname;
    private String gender;
    private LocalDate dateOfBirth;
    private String course;
    private String country;
    private String status;
    private int number;
    public StudentData() {}
    public StudentData(int number, String name, String surname, String gender, LocalDate dateOfBirth, String course, String country, String status, int studentId) {
        this.number = number;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.course = course;
        this.country = country;
        this.status = status;
        this.studentId = studentId;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId() {
        Random random = new Random();
        this.studentId = random.nextInt(1000) + 1;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
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
