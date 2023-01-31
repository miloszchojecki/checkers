package com.example.checkers.client.bot;

import com.example.checkers.client.ClientCommunicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class BotApplication
{
    static BufferedReader serverInput;
    static PrintWriter serverOutput;
    static int clientNumber;
    static BotGameHandler botGameHandler;

    static final String[] gameModes = {"Warcaby angielskie", "Warcaby włoskie", "Warcaby dwuliniowe"};

    private static Socket socket;
    public static void main(String[] args)
    {
        try
        {
            socket = new Socket("localhost", 4444);
            serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            serverOutput = new PrintWriter(socket.getOutputStream(), true);

            clientNumber = Integer.parseInt(serverInput.readLine());


            if(clientNumber == 1)
            {
                Random random = new Random();
                int gameMode = random.nextInt(3);
                System.out.println("Playing as player 1, starting " + gameModes[gameMode]);
                serverOutput.println(gameModes[gameMode]);
                startGame();
            }
            else if(clientNumber == 2)
            {
                System.out.println("Playing as player 2");
                startGame();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void startGame()
    {
        botGameHandler = new BotGameHandler(clientNumber, new ClientCommunicator(serverInput, serverOutput));
    }
}
