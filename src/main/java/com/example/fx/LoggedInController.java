package com.example.fx;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class LoggedInController {
    @FXML
    private Button button_switch_to_reg;

    @FXML
    public void initialize() {
        // When the sign-up button is pressed, switch to sign-up scene
        button_switch_to_reg.setOnAction((ActionEvent event) -> {
            DBUtils.changeScene(event, "sign-up.fxml", null);
        });
    }
}
