module com.example.checkers {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.checkers to javafx.fxml;
    //exports com.example.checkers;
    exports com.example.checkers.client;
    opens com.example.checkers.client to javafx.fxml;
    exports com.example.checkers.server;
    opens com.example.checkers.server to javafx.fxml;
    exports com.example.checkers.gamelogic;
    opens com.example.checkers.gamelogic to javafx.fxml;
    exports com.example.checkers.data;
    opens com.example.checkers.data to javafx.fxml;
    exports com.example.checkers.common;
    opens com.example.checkers.common to javafx.fxml;
}