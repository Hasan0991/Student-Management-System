package com.example.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AdminView {
    @FXML
    private TableView<StudentData> adminTableView;

    @FXML
    private TableColumn<StudentData, String> admin_student_name;
    @FXML
    private TableColumn<StudentData, String> admin_student_surname;
    @FXML
    private TableColumn<StudentData, String> admin_student_country;
    @FXML
    private TableColumn<StudentData, String> admin_student_course;
    @FXML
    private TableColumn<StudentData, String> admin_student_date;
    @FXML
    private TableColumn<StudentData, String> admin_student_gender;

    @FXML
    private TableColumn<StudentData, String> admin_student_number;

    @FXML
    private TableColumn<StudentData, String> admin_student_status;
    @FXML
    private TableColumn<StudentData, String> admin_student_id;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_add_course;

    @FXML
    private Button btn_add_dashboard;

    @FXML
    private Button btn_add_salary;

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

    private ObservableList<StudentData> studentData = FXCollections.observableArrayList();
    @FXML
    private void initialize() {
        admin_student_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        admin_student_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        admin_student_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        admin_student_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        admin_student_date.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        admin_student_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        admin_student_country.setCellValueFactory(new PropertyValueFactory<>("country"));
        admin_student_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        admin_student_id.setCellValueFactory(new PropertyValueFactory<>("studentId"));

        loadStudentDataFromDatabase();
        adminTableView.setItems(studentData);
        btn_delete.setOnAction(e -> deleteStudentFromDatabase());
    }

    private void loadStudentDataFromDatabase() {
        DBUtils dbUtils = new DBUtils();
        studentData.setAll(dbUtils.getStudents());
    }

    private void deleteStudentFromDatabase() {

        StudentData selectedStudent = adminTableView.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Are you sure you want to delete this student?");
            alert.setContentText("This action cannot be undone.");

            Optional<ButtonType> result = alert.showAndWait();


            if (result.isPresent() && result.get() == ButtonType.OK) {
                int studentId = selectedStudent.getStudentId();
                DBUtils dbUtils = new DBUtils();
                boolean answer = dbUtils.deleteSelectedStudent(studentId);
                if (answer) {
                    studentData.remove(selectedStudent);
                    System.out.println("Student deleted successfully.");
                } else {
                    System.out.println("Failed to delete student.");
                }
            } else {

                System.out.println("Deletion canceled.");
            }
        } else {
            System.out.println("No student selected.");
        }
    }
    @FXML
    public void switch_to_addUser(ActionEvent event) {
        DBUtils.changeScene(event, "add-student .fxml", null);
    }
    @FXML
    public void switch_to_update(ActionEvent event) {
        StudentData selectedStudent = adminTableView.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            DBUtils.showAlert(Alert.AlertType.ERROR, "ERROR", "Please select a student");
        } else {
            int studentId = selectedStudent.getStudentId();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("update-student-info.fxml"));
                Parent root = loader.load();


                UpdateStudentInfo updateStudentInfoController = loader.getController();
                updateStudentInfoController.initializeStudentId(studentId);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                DBUtils.showAlert(Alert.AlertType.ERROR, "ERROR", "Failed to load the update-student-info.fxml file.");
            }
        }
    }


}
