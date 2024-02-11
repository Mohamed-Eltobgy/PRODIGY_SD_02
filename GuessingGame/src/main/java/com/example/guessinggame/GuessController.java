package com.example.guessinggame;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.util.Random;
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

    private Integer generatedNumber, noOfAttempts;

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
        TextFormatter<String> lowerFormatter = new TextFormatter<>(filter);
        TextFormatter<String> upperFormatter = new TextFormatter<>(filter);
        TextFormatter<String> resultFormatter = new TextFormatter<>(filter);
        lowerField.setTextFormatter(lowerFormatter);
        upperField.setTextFormatter(upperFormatter);
        answer.setTextFormatter(resultFormatter);

        result.setAlignment(Pos.CENTER);
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
    @FXML
    public void generateNumber(ActionEvent e){
        ScaleTransition generateScale = new ScaleTransition(Duration.millis(80), generate);
        generateScale.setFromX(1.0);
        generateScale.setFromY(1.0);
        generateScale.setToX(0.965);
        generateScale.setToY(0.965);
        generateScale.play();
        generateScale.setOnFinished(event ->{
            generate.setScaleX(1.0);
            generate.setScaleY(1.0);
        });

        if ((lowerField.getText().length() == 1 && lowerField.getText().charAt(0) == '-')
                || lowerField.getText().length() == 0) {
            showAlert("Please enter a valid Lower Bound value.");
        } else if ((upperField.getText().length() == 1 && upperField.getText().charAt(0) == '-')
                || upperField.getText().length() == 0) {
            showAlert("Please enter a valid Upper Bound value.");
        } else {
            int lowerNumber = Integer.parseInt(lowerField.getText());
            int upperNumber = Integer.parseInt(upperField.getText());
            if (lowerNumber > upperNumber){
                showAlert("Lower bound value can not be larger than Upper bound value.");
            } else {
                generatedNumber = new Random().nextInt(upperNumber - lowerNumber + 1) + lowerNumber;
                noOfAttempts = 0;
                result.setText("");
                result.setStyle("-fx-background-color: transparent");
            }
        }
    }

    public void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error Occurred");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void submitAnswer(ActionEvent e){
        ScaleTransition submitScale = new ScaleTransition(Duration.millis(90), submit);
        submitScale.setFromX(1.0);
        submitScale.setFromY(1.0);
        submitScale.setToX(0.94);
        submitScale.setToY(0.94);
        submitScale.play();
        submitScale.setOnFinished(event -> {
            submit.setScaleX(1.0);
            submit.setScaleY(1.0);
        });
        if(generatedNumber == null){
            showAlert("Please generate a random number first, then guess it.");
        } else  if ((answer.getText().length() == 1 && answer.getText().charAt(0) == '-' )
                || answer.getText().length() == 0) {
            showAlert("Please enter a valid guess.");
        } else {
            Integer guess = Integer.parseInt(answer.getText());
            noOfAttempts++;
            if (guess > generatedNumber) {
                result.setText("Too High");
                result.setStyle("-fx-background-color: Red");
            }else if (guess < generatedNumber) {
                result.setText("Too Low");
                result.setStyle("-fx-background-color: #ffef00");
            } else {
                result.setText("Congratulations! You have guessed correctly.\nNumber of Attempts: " + noOfAttempts);
                result.setStyle("-fx-background-color: Green");
                noOfAttempts = 0;
            }
        }
    }
}