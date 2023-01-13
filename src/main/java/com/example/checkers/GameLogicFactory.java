package com.example.checkers;

import java.util.Objects;

public class GameLogicFactory
{
    private Board board;

    public GameLogicFactory(Board board)
    {
        this.board = board;
    }

    public GameLogic getGameLogic(String gameMode)
    {
        if(gameMode.equals("Warcaby angielskie"))
            return new EnglishCheckersGameLogic(board);
        if(gameMode.equals("Warcaby włoskie"))
            return new ItalianCheckersGameLogic(board);
        if(gameMode.equals("Warcaby dwuliniowe"))
            return new TwoLineCheckersGameLogic(board);
        return new DummyGameLogic(board);
    }
}
