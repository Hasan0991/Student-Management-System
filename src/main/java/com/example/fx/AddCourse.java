package com.example.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddCourse {
    @FXML
    private TextField add_course_text_field;

    @FXML
    private Button btn_add_course;
    @FXML
    public void addCourseToDatabase(ActionEvent event) {
        String courseName = add_course_text_field.getText().trim();

        if (courseName.isEmpty()) {
            showAlert("Input Error", "Course name cannot be empty.");
            return;
        }

        if (!isCourseExists(courseName)) {
            insertCourse(courseName);
            showAlert("Success", "Course added successfully.");
            DBUtils.changeScene(event,"admin-view.fxml",null);
        } else {
            showAlert("Duplicate Course", "Course already exists.");
        }
    }

    private boolean isCourseExists(String courseName) {
        String checkQuery = "SELECT COUNT(*) FROM course WHERE course_name = ?";

        try (Connection conn  = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "hasan099");
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {

            checkStmt.setString(1, courseName);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    private void insertCourse(String courseName) {
        String insertQuery = "INSERT INTO course (course_name) VALUES (?)";

        try (Connection conn  = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "hasan099");
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            insertStmt.setString(1, courseName);
            insertStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
