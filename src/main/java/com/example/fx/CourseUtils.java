package com.example.fx;

import javafx.scene.control.ComboBox;

public class CourseUtils {
    public static void populateCourseComboBox(ComboBox<String> comboBox) {
        comboBox.getItems().addAll("Computer Science", "Computer Systems", "Economics");
    }
    public static void populateGender(ComboBox<String> comboBox) {
        comboBox.getItems().addAll("Male", "Female", "Other");
    }
    public static void populateStartedYear(ComboBox<String> comboBox) {
        comboBox.getItems().addAll("2021", "2022", "2023","2024");
    }
}
