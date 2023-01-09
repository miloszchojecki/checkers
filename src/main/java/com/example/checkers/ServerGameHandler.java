package com.example.checkers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerGameHandler implements Runnable
{
    private Socket firstPlayer;
    private Socket secondPlayer;

    BufferedReader firstInput, secondInput;
    PrintWriter firstOutput, secondOutput;

    public ServerGameHandler(Socket firstPlayer, Socket secondPlayer)
    {
        try
        {
            this.firstPlayer = firstPlayer;
            this.secondPlayer = secondPlayer;
            firstInput = new BufferedReader(new InputStreamReader(firstPlayer.getInputStream()));
            secondInput = new BufferedReader(new InputStreamReader(secondPlayer.getInputStream()));
            firstOutput = new PrintWriter(firstPlayer.getOutputStream(), true);
            secondOutput = new PrintWriter(secondPlayer.getOutputStream(), true);
            firstOutput.println("1");
            secondOutput.println("2");
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void run()
    {

    }
}
