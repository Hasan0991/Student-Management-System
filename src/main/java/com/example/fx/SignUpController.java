package com.example.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignUpController {
    @FXML
    private Button button_loginpage_reg;

    @FXML
    private Button button_sign_up_reg;

    @FXML
    private TextField tf_username_reg;

    @FXML
    private TextField tf_password_reg_1;

    @FXML
    private TextField tf_password_reg_2;

    @FXML
    private TextField tf_email_reg;

    @FXML
    public void initialize() {
        // When the sign-up button is pressed, switch to sign-in scene
        button_loginpage_reg.setOnAction((ActionEvent event) -> {
            DBUtils.changeScene(event, "sign-in.fxml", null);
        });
    }
    @FXML
    public void signUp(ActionEvent event) {
        String username = tf_username_reg.getText();
        String password = tf_password_reg_1.getText();
        String password1 = tf_password_reg_2.getText();
        String email = tf_email_reg.getText();


        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || password1.isEmpty()) {
            DBUtils.showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled out");
            return;
        }

        if (!DBUtils.isValidEmail(email)) {
            DBUtils.showAlert(Alert.AlertType.ERROR, "Error", "Invalid email format or domain. Please use a Gmail address.");
            return;
        }


        if (!password.equals(password1)) {
            DBUtils.showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match");
            return;
        }

        DBUtils dbUtils = new DBUtils();
        dbUtils.SignUpUser(event, username, password, email);
    }

}
