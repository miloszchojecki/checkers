package com.example.checkers.client;

import com.example.checkers.common.CommandQueue;
import javafx.animation.AnimationTimer;

public class ClientUpdater extends AnimationTimer
{
    private CommandQueue commandQueue;
    private ClientGameHandler clientGameHandler;

    public ClientUpdater(CommandQueue commandQueue, ClientGameHandler clientGameHandler)
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
