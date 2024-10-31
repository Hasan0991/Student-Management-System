package com.example.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class UpdateStudentInfo {
    private int studentId;
    @FXML
    private ComboBox<String> update_student_combox;


    @FXML
    private Button update_student_save_btn;

    @FXML
    public void initialize() {
        CourseUtils.populateCourseComboBox(update_student_combox);
    }

    public void initializeStudentId(int studentId) {
        this.studentId = studentId;
    }
    @FXML
    public void onSave(ActionEvent event) {
        System.out.println("i am here");
        DBUtils db = new DBUtils();
        boolean flag = db.replace_studentData(studentId, update_student_combox.getValue());
        if (flag){
            DBUtils.showAlert(Alert.AlertType.CONFIRMATION,"Congrutulations","you have replaced data corectly");
            DBUtils.changeScene(event,"admin-view.fxml","null");
        }
        else {
            DBUtils.showAlert(Alert.AlertType.ERROR,"ERROR", "something went wrong");
        }
    }


}