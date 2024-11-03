package com.example.fx;

import javafx.scene.control.ComboBox;

import java.sql.*;

public class CourseUtils {

    public static void populateCourseComboBox(ComboBox<String> comboBox) {
        String query = "SELECT course_name FROM course";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "hasan099");
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            comboBox.getItems().clear();
            while (rs.next()) {
                String courseName = rs.getString("course_name");
                comboBox.getItems().add(courseName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void populateGender(ComboBox<String> comboBox) {
        comboBox.getItems().clear();
        comboBox.getItems().addAll("Male", "Female", "Other");
    }

    public static void populateStartedYear(ComboBox<String> comboBox) {
        comboBox.getItems().clear();
        comboBox.getItems().addAll("2021", "2022", "2023", "2024");
    }

}
