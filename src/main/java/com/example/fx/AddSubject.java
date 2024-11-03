package com.example.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddSubject {

    @FXML
    private Button add_subject_btn;

    @FXML
    private TableView<Course> course_table;
    @FXML
    private TableColumn<Course, Integer> id_column;
    @FXML
    private TableColumn<Course, String> nameColumn;


    @FXML
    private TextField etc_point_text_field;

    @FXML
    private TextField name_subject_text_field;

    @FXML
    public void initialize() {

        id_column.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));

        DBUtils dbUtils = new DBUtils();
        ObservableList<Course> courses = dbUtils.getCourses();
        course_table.setItems(courses);
    }
    @FXML
    private void add_subject() {
        Course selectedCourse = course_table.getSelectionModel().getSelectedItem();
        String subjectName = name_subject_text_field.getText().trim();
        int etcPoints = Integer.parseInt(etc_point_text_field.getText().trim());
        if (selectedCourse != null && !subjectName.isEmpty()) {
            DBUtils dbUtils = new DBUtils();
            dbUtils.insertSubject(selectedCourse.getCourseId(), subjectName, etcPoints);
            ObservableList<Subject> subjects = dbUtils.getSubjects(selectedCourse.getCourseId());
            for(Subject subject :subjects){
                System.out.println("Subject Name: " + subject.getSubjectName() + ", ETC Points: " + subject.getEtc_points());
            }
        } else {
            DBUtils.showAlert(Alert.AlertType.ERROR, "Error", "Please select a course and enter subject details.");
        }
    }
}
