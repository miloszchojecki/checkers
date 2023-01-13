package com.example.checkers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args)
    {
        try (ServerSocket serverSocket = new ServerSocket(4444)) {

            System.out.println("Server is listening on port 4444");

            Socket firstPlayer = serverSocket.accept();
            System.out.println("First client connected");
            System.out.println("Waiting for the second player");

            Socket secondPlayer = serverSocket.accept();
            System.out.println("Second client connected");

            BufferedReader firstInput = new BufferedReader(new InputStreamReader(firstPlayer.getInputStream()));
            BufferedReader secondInput = new BufferedReader(new InputStreamReader(secondPlayer.getInputStream()));
            PrintWriter firstOutput = new PrintWriter(firstPlayer.getOutputStream(), true);
            PrintWriter secondOutput = new PrintWriter(secondPlayer.getOutputStream(), true);

            firstOutput.println("1");
            secondOutput.println("2");

            String gameMode = firstInput.readLine();

            GameLogicFactory gameLogicFactory = new GameLogicFactory(new Board());
            GameLogic gameLogic = gameLogicFactory.getGameLogic(gameMode);

            //GameLogic gameLogic = new DummyGameLogic(new Board());

            ServerGameHandler serverGameHandler = new ServerGameHandler(gameLogic, firstInput, firstOutput, secondInput, secondOutput);
            serverGameHandler.playGame();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
