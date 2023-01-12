package com.example.checkers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientGUIController implements Initializable
{
    @FXML
    private VBox vbox;
    @FXML
    private Label label;
    @FXML
    private Button startButton;
    @FXML
    private ChoiceBox choiceBox;

    private Game game;

    private Socket socket;
    BufferedReader serverInput;
    PrintWriter serverOutput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            socket = new Socket("localhost", 4444);
            serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            serverOutput = new PrintWriter(socket.getOutputStream(), true);

            int clientNumber = Integer.parseInt(serverInput.readLine());

            if(clientNumber == 1)
            {
                label.setText(TextStrings.youAreFirstPlayer);
            }
            else if(clientNumber == 2)
            {
                label.setText(TextStrings.youAreSecondPlayer);
                choiceBox.setVisible(false);
                startButton.setVisible(false);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        Board gameBoard = new Board();
        vbox.getChildren().add(gameBoard.getGridPane());
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