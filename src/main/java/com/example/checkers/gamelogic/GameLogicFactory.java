package com.example.checkers.gamelogic;

import com.example.checkers.common.Board;

/**
 * class of design pattern Factory
 */
public class GameLogicFactory
{
    private Board board;
    /**
     * GameLogicFactory constructor
     */
    public GameLogicFactory(Board board)
    {
        this.board = board;
    }
    /**
     * Method that gets gameLogic
     */
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
