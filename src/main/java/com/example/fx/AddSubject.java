package com.example.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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


}
