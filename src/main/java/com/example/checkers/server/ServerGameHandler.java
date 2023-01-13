package com.example.checkers.server;

import com.example.checkers.common.Command;
import com.example.checkers.common.CommandType;
import com.example.checkers.common.MoveInfo;
import com.example.checkers.common.TileCoordinates;
import com.example.checkers.gamelogic.GameLogic;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ServerGameHandler
{
    BufferedReader firstInput, secondInput;
    PrintWriter firstOutput, secondOutput;

    GameLogic gameLogic;
    ServerCommunicator serverCommunicator;

    public ServerGameHandler(GameLogic gameLogic, BufferedReader firstInput, PrintWriter firstOutput, BufferedReader secondInput, PrintWriter secondOutput)
    {
            this.gameLogic = gameLogic;
            this.firstInput = firstInput;
            this.firstOutput = firstOutput;
            this.secondInput = secondInput;
            this.secondOutput = secondOutput;
            serverCommunicator = new ServerCommunicator(firstInput, firstOutput, secondInput, secondOutput);
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
                    if (moveInfo != null)
                    {
                        serverCommunicator.sendMoveInfo(moveFromCoordinates, moveToCoordinates, moveInfo);
                    }
                }
        }
    }
}
