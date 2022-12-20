package com.example.checkers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable
{
    @FXML
    private VBox vbox;

    private GridPane gridPane;

    private GameLogic gameLogic;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Board board = new Board();
        gridPane = board.getGridPane();
        vbox.getChildren().add(gridPane);
        gameLogic = new DummyGameLogic(board);
        gameLogic.initialize();
    }
}