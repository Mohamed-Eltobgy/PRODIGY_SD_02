package com.example.guessinggame;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.Duration;

import java.util.function.UnaryOperator;

public class GuessController {
    @FXML
    private Label title;
    @FXML
    private Label lower;
    @FXML
    private Label upper;
    @FXML
    private Label guess;
    @FXML
    private Label result;
    @FXML
    private Button generate;
    @FXML
    private Button submit;
    @FXML
    private TextField lowerField;
    @FXML
    private TextField upperField;
    @FXML
    private TextField answer;
    Node[] components;

    public void initialize() {
        components = new Node[]{title, lower, upper, guess, result, generate, submit,
                lowerField, upperField, answer};
        ParallelTransition allItems = createFadeTransitions(components);
        allItems.setDelay(Duration.seconds(1));
        allItems.play();

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getControlNewText();
            if(text.matches("-?\\d*")){
                return change;
            }
            return null;
        };
        TextFormatter<String> lowerFormaatter = new TextFormatter<>(filter);
        TextFormatter<String> upperFormatter = new TextFormatter<>(filter);
        TextFormatter<String> resultFormatter = new TextFormatter<>(filter);
        lowerField.setTextFormatter(lowerFormaatter);
        upperField.setTextFormatter(upperFormatter);
        answer.setTextFormatter(resultFormatter);
    }

    public ParallelTransition createFadeTransitions(Node[] components) {
        FadeTransition[] fadeTransitions = new FadeTransition[components.length];
        for (int i = 0; i < components.length; i++) {
            fadeTransitions[i] = createFadeTransition(components[i]);
        }
        return new ParallelTransition(fadeTransitions);
    }

    private FadeTransition createFadeTransition(Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), node);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        return fadeTransition;
    }
}