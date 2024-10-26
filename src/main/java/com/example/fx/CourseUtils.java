package com.example.fx;

import javafx.scene.control.ComboBox;

public class CourseUtils {
    public static void populateCourseComboBox(ComboBox<String> comboBox) {
        comboBox.getItems().addAll("Course 1", "Course 2", "Course 3");
    }
}
