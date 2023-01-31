package com.example.checkers.client;

import com.example.checkers.common.Command;

public interface CommandReceiver
{
    public void update(Command command);
}
