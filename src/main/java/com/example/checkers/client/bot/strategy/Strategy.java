package com.example.checkers.client.bot.strategy;

import com.example.checkers.common.Board;
import com.example.checkers.common.MoveCoordinates;

import java.util.ArrayList;

public abstract class Strategy
{
    private Board gameBoard;
    public abstract MoveCoordinates makeMove(ArrayList<MoveCoordinates> possibleMoves);
}
