package com.example.checkers;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board
{
    private GridPane gridPane;

    private Tile[][] tiles;

    public Board()
    {
        gridPane = new GridPane();
        tiles = new Tile[8][8];

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                tiles[i][j] = new Tile();
                gridPane.add(tiles[i][j].getStackPane(), i, j);
                tiles[i][j].setPlayable((i + j) % 2 == 1);
            }
        }
    }

    public GridPane getGridPane()
    {
        return gridPane;
    }

    public Tile[][] getTiles()
    {
        return tiles;
    }
}
