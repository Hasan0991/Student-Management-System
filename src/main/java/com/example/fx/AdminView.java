package com.example.fx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class AdminView {
    @FXML
    private TableColumn<?, ?> admin_student_country;

    @FXML
    private TableColumn<?, ?> admin_student_course;

    @FXML
    private TableColumn<?, ?> admin_student_date;

    @FXML
    private TableColumn<?, ?> admin_student_gender;

    @FXML
    private TableColumn<?, ?> admin_student_name;

    @FXML
    private TableColumn<?, ?> admin_student_number;

    @FXML
    private TableColumn<?, ?> admin_student_status;

    @FXML
    private TableColumn<?, ?> admin_student_surname;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_add_course;

    @FXML
    private Button btn_add_dashboard;

    @FXML
    private Button btn_add_salalry;

    @FXML
    private Button btn_add_student;

    @FXML
    private Button btn_add_subject;

    @FXML
    private Button btn_add_teacher;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_update;

}
