package com.example.checkers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientGUIController implements Initializable
{
    @FXML
    private VBox vbox;
    @FXML
    private Label textBox;
    private GridPane gridPane;
    @FXML
    private Button startButton;

    private Game game;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        game = new Game(textBox);
        gridPane = game.getBoard().getGridPane();
        vbox.getChildren().add(gridPane);
        startButton.setOnAction(actionEvent -> startButtonHandler());
    }

    void startButtonHandler()
    {
        newGame();
        //startButton.setDisable(true);
    }

    void newGame()
    {
        game.startGame();
    }
}