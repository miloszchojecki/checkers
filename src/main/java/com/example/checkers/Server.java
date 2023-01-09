package com.example.checkers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args)
    {
        try (ServerSocket serverSocket = new ServerSocket(4444)) {

            System.out.println("Server is listening on port 4444");

                Socket firstClient = serverSocket.accept();
                System.out.println("First client connected");
                System.out.println("Waiting for the second player");

                Socket secondClient = serverSocket.accept();
                System.out.println("Second client connected");

                ServerGameHandler game = new ServerGameHandler(firstClient, secondClient);
                Thread gameThread = new Thread(game);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
