package com.example.checkers.client;

import com.example.checkers.common.Board;
import com.example.checkers.data.TextStrings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * class of ClientGUIController
 */
public class ClientGUIController implements Initializable
{
    @FXML
    private VBox vbox;
    @FXML
    private Label label;
    @FXML
    private Button startButton;
    @FXML
    private ComboBox<String> choiceBox;

    BufferedReader serverInput;
    PrintWriter serverOutput;
    int clientNumber;
    Board gameBoard;
    ClientGameHandler clientGameHandler;

    private Socket socket;

    final String[] gameModes = {"Warcaby angielskie", "Warcaby włoskie", "Warcaby dwuliniowe"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        choiceBox.getItems().addAll(gameModes);

        gameBoard = new Board();
        GridPane gridPane = gameBoard.getGridPane();
        vbox.getChildren().add(gridPane);
        startButton.setOnAction(actionEvent -> startButtonHandler());

        try
        {
            socket = new Socket("localhost", 4444);
            serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            serverOutput = new PrintWriter(socket.getOutputStream(), true);

            clientNumber = Integer.parseInt(serverInput.readLine());


            if(clientNumber == 1)
            {
                label.setText(TextStrings.youAreFirstPlayer);
            }
            else if(clientNumber == 2)
            {
                label.setText(TextStrings.youAreSecondPlayer);
                choiceBox.setVisible(false);
                startButton.setVisible(false);
                gridPane.setRotate(180);
                startGame();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    void startButtonHandler()
    {
        serverOutput.println(choiceBox.getValue());
        choiceBox.setDisable(true);
        startButton.setDisable(true);
        startGame();
    }

    void startGame()
    {
        label.setVisible(false);

        clientGameHandler = new ClientGameHandler(clientNumber, gameBoard, label, new ClientCommunicator(serverInput, serverOutput));
        clientGameHandler.update();
    }
}