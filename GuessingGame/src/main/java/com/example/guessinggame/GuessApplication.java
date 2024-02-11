package com.example.guessinggame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GuessApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GuessApplication.class.getResource("Guess-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Image icon = new Image(getClass().getResource(("icon.png")).toExternalForm());
        stage.getIcons().add(icon);
        stage.setTitle("Guess The Number");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}