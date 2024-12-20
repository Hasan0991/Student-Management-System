package com.example.fx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class AddStudent {

    @FXML
    private Button add_new_student_button;
    @FXML
    private Button add_student_back_button;
    @FXML
    private DatePicker add_student_birth_date;

    @FXML
    private ComboBox<String> add_student_course;

    @FXML
    private ComboBox<String> add_student_gender;
    @FXML
    private TextField add_student_country;
    @FXML
    private TextField add_student_last_name;
    @FXML
    private TextField add_student_name_field;

    @FXML
    private ComboBox<String> add_student_start_year;

    @FXML
    private void initialize() {

        CourseUtils.populateCourseComboBox(add_student_course);
        CourseUtils.populateStartedYear(add_student_start_year);
        CourseUtils.populateGender(add_student_gender);
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


        if (firstName.isEmpty() || lastName.isEmpty() || gender == null || course == null || startedYear == null || birthDate == null || country.isEmpty()) {
            DBUtils.showAlert(Alert.AlertType.ERROR,"ERROR","PLEASE FILL EVERYTHING");
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
        student.setNumber(0);
        student.setStatus("Active");
        student.setStudentId();
        System.out.println("everything up to date");

        DBUtils dbUtils = new DBUtils();
        dbUtils.AddStudent(event, student);
        dbUtils.showAlert(Alert.AlertType.INFORMATION, "Congratulations", "Everything is okay");
        dbUtils.changeScene(event,"admin-view.fxml","null");
    }

    @FXML
    public void addNewStudentButtonClicked(ActionEvent event) {
        handleAddStudent(event);
    }
    public void back_to_admin(ActionEvent event) {
        DBUtils.changeScene(event,"admin-view.fxml","admin");
    }
}
