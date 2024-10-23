package com.example.fx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class AddStudent {

    @FXML
    private Button add_new_student_button;

    @FXML
    private DatePicker add_student_birth_date;

    @FXML
    private ComboBox<String> add_student_course; // Use specific type for ComboBox

    @FXML
    private ComboBox<String> add_student_gender; // Use specific type for ComboBox
    @FXML
    private TextField add_student_country;
    @FXML
    private TextField add_student_last_name;
    @FXML
    private TextField add_student_name_field;

    @FXML
    private ComboBox<String> add_student_start_year; // Use specific type for ComboBox

    @FXML
    private void initialize() {
        // Optionally populate the ComboBoxes with data (e.g., courses, genders, etc.)
        add_student_course.getItems().addAll("Course 1", "Course 2", "Course 3");
        add_student_gender.getItems().addAll("Male", "Female", "Other");
        add_student_start_year.getItems().addAll("2020", "2021", "2022", "2023", "2024");
    }

    @FXML
    public void handleAddStudent(ActionEvent event) {

        String firstName = add_student_name_field.getText();
        String lastName = add_student_last_name.getText();
        String country = add_student_country.getText();
        String gender = add_student_gender.getValue();
        String course = add_student_course.getValue();
        String startedYear = add_student_start_year.getValue();
        java.time.LocalDate birthDate = add_student_birth_date.getValue();


        if (firstName.isEmpty() || lastName.isEmpty() || gender == null || course == null || startedYear == null || birthDate == null) {
            System.out.println("Please fill in all fields.");
            return;
        }


        StudentData student = new StudentData();
        student.setNumber(0);
        student.setName(firstName);
        student.setSurname(lastName);
        student.setGender(gender);
        student.setCourse(course);
        student.setDateOfBirth(birthDate);
        student.setCountry(country);
        student.setNumber(123);
        student.setStatus("Active");
        student.setStudentId(1);
        System.out.println("everything up to date");

        DBUtils dbUtils = new DBUtils();
        dbUtils.AddStudent(event, student);
    }

    @FXML
    public void addNewStudentButtonClicked(ActionEvent event) {
        handleAddStudent(event);
    }
}
