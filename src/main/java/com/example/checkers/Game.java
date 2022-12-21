package com.example.checkers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Game
{
    private GameLogic gameLogic;

    private Board board;

    private Tile[][] tiles;

    public Game()
    {
        board = new Board();
        tiles = board.getTiles();
        gameLogic = new DummyGameLogic(board);
        gameLogic.initialize();

        for(int i=0; i<tiles.length; i++)
            for (int j=0; j<tiles[0].length; j++)
            {
                int finalI = i;
                int finalJ = j;
                tiles[i][j].getStackPane().setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        clickHandle(event, finalI, finalJ);
                    }
                });
            }
    }

    public void clickHandle(MouseEvent event, int tileX, int tileY)
    {
        System.out.println(tileX + " mak " + tileY);
    }
    public Board getBoard()
    {
        return board;
    }
}
