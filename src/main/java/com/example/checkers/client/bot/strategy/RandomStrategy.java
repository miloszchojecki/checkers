package com.example.checkers.client.bot.strategy;
import com.example.checkers.common.MoveCoordinates;

import java.util.ArrayList;
import java.util.Random;

public class RandomStrategy extends Strategy
{
    Random random;

    public RandomStrategy()
    {
        random = new Random();
    }

    public MoveCoordinates makeMove(ArrayList<MoveCoordinates> possibleMoves)
    {
        try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException ignored) {}

        return possibleMoves.get(random.nextInt(possibleMoves.size()));
    }
}
