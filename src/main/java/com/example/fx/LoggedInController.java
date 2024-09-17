package com.example.fx;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoggedInController {
    @FXML
    private Button button_switch_to_reg;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;
    @FXML
    private Button button_login;


    @FXML
    public void initialize() {
        // When the sign-up button is pressed, switch to sign-up scene
        button_switch_to_reg.setOnAction((ActionEvent event) -> {
            DBUtils.changeScene(event, "sign-up.fxml", null);
        });
    }
    public void Result(ActionEvent event){
        String email = tf_email.getText();
        String password = tf_password.getText();
        if(email.isEmpty() || password.isEmpty()){
            DBUtils.showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled out");
            return;
        }
        DBUtils dbUtils = new DBUtils();
        dbUtils.SignInUser(event, email,password);
    }
}
