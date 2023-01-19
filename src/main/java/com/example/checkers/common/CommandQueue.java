package com.example.checkers.common;

import java.util.ArrayDeque;
import java.util.Queue;

public class CommandQueue
{
    private final Queue<Command> queue = new ArrayDeque<>();

    public void addCommand(Command command)
    {
        queue.add(command);
    }

    public Command getCommand()
    {
        return queue.poll();
    }

    public boolean isCommand()
    {
        return queue.peek() != null;
    }
}
