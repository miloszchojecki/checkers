package com.example.checkers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerGameHandler
{
    BufferedReader firstInput, secondInput;
    PrintWriter firstOutput, secondOutput;

    GameLogic gameLogic;

    ServerCommunicator serverCommunicator;

    PieceColor turn;

    public ServerGameHandler(GameLogic gameLogic, BufferedReader firstInput, PrintWriter firstOutput, BufferedReader secondInput, PrintWriter secondOutput)
    {
            this.gameLogic = gameLogic;
            this.firstInput = firstInput;
            this.firstOutput = firstOutput;
            this.secondInput = secondInput;
            this.secondOutput = secondOutput;
            serverCommunicator = new ServerCommunicator(firstInput, firstOutput, secondInput, secondOutput);
    }

    private BufferedReader getPlayerInput(PieceColor player)
    {
        return (player == PieceColor.WHITE ? firstInput : secondInput);
    }

    private PrintWriter getPlayerOutput(PieceColor player)
    {
        return (player == PieceColor.WHITE ? firstOutput : secondOutput);
    }

    public void playGame() throws IOException
    {
        gameLogic.initialize();
        serverCommunicator.initialize(gameLogic.getTiles());
        serverCommunicator.setTurn(gameLogic.getTurn());

        while (true)
        {
            BufferedReader in = getPlayerInput(gameLogic.getTurn());
            String message = in.readLine();
            if(message.charAt(0) == 's')
            {
                TileCoordinates selectedTileCoordinates = serverCommunicator.getSelectedTile(message);

                ArrayList<TileCoordinates> possibleMoves = gameLogic.getPossibleMoves(selectedTileCoordinates);
                serverCommunicator.sendPossibleMoves(gameLogic.getTurn(), possibleMoves);
            }
            else if(message.charAt(0) == 'm')
            {
                TileCoordinates moveFromCoordinates = serverCommunicator.getFromCoordinates(message);
                TileCoordinates moveToCoordinates = serverCommunicator.getToCoordinates(message);
                MoveInfo moveInfo = gameLogic.makeMove(moveFromCoordinates, moveToCoordinates);
                if(moveInfo != null)
                {
                    serverCommunicator.sendMoveInfo(moveFromCoordinates, moveToCoordinates, moveInfo);
                }
            }
        }
    }
}
