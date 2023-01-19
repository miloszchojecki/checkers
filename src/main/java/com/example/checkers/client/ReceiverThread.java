package com.example.checkers.client;

import com.example.checkers.common.Command;
import com.example.checkers.common.CommandQueue;


public class ReceiverThread extends Thread
{
    private final ClientCommunicator clientCommunicator;
    private final CommandQueue commandQueue;

    public ReceiverThread(ClientCommunicator clientCommunicator, CommandQueue commandQueue)
    {
        this.clientCommunicator = clientCommunicator;
        this.commandQueue = commandQueue;
    }

    @Override
    public void run()
    {
        while(true)
        {
            Command command = clientCommunicator.getCommand();
            commandQueue.addCommand(command);
        }
    }
}
