package com.example.fx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class UpdateStudentInfo {

    @FXML
    private ComboBox<String> update_student_combox;

    @FXML
    private Button update_student_save_btn;

    @FXML
    public void initialize() {
        CourseUtils.populateCourseComboBox(update_student_combox);
    }

}