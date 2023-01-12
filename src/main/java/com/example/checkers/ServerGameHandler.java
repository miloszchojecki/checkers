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

    public void playGame()
    {
        gameLogic.initialize();
        serverCommunicator.initialize(gameLogic.getTiles());
        serverCommunicator.setTurn(gameLogic.getTurn());

        while (true)
        {
                Command command = serverCommunicator.getCommand(gameLogic.getTurn());
                if (command.getCommandType() == CommandType.SELECT)
                {
                    TileCoordinates selectedTileCoordinates = command.getCoordinates();
                    ArrayList<TileCoordinates> possibleMoves = gameLogic.getPossibleMoves(selectedTileCoordinates);
                    serverCommunicator.sendPossibleMoves(gameLogic.getTurn(), possibleMoves);
                }
                else if (command.getCommandType() == CommandType.MOVE)
                {
                    TileCoordinates moveFromCoordinates = command.getCoordinates();
                    TileCoordinates moveToCoordinates = command.getNextCoordinates();
                    MoveInfo moveInfo = gameLogic.makeMove(moveFromCoordinates, moveToCoordinates);
                    System.out.println("test");
                    if (moveInfo != null)
                    {
                        serverCommunicator.sendMoveInfo(moveFromCoordinates, moveToCoordinates, moveInfo);
                    }
                }
        }
    }
}
