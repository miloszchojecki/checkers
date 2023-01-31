package com.example.checkers.client;

import com.example.checkers.common.CommandQueue;
import javafx.animation.AnimationTimer;

public class ClientUpdater extends AnimationTimer
{
    private final CommandQueue commandQueue;
    private final CommandReceiver clientGameHandler;

    public ClientUpdater(CommandQueue commandQueue, CommandReceiver clientGameHandler)
    {
        this.commandQueue = commandQueue;
        this.clientGameHandler = clientGameHandler;
    }

    @Override
    public void handle(long l)
    {
        if(commandQueue.isCommand())
        {
            clientGameHandler.update(commandQueue.getCommand());
        }
    }
}
