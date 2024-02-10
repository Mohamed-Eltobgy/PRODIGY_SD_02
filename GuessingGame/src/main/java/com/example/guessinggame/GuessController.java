package com.example.guessinggame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GuessController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}