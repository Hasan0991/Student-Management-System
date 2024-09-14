package com.example.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SignUpController {
    @FXML
    private Button button_loginpage_reg;

    @FXML
    public void initialize() {
        // When the sign-up button is pressed, switch to sign-up scene
        button_loginpage_reg.setOnAction((ActionEvent event) -> {
            DBUtils.changeScene(event, "sign-in.fxml", null);
        });
    }
}
