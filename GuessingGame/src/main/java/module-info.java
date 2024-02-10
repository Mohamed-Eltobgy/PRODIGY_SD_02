module com.example.guessinggame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.guessinggame to javafx.fxml;
    exports com.example.guessinggame;
}