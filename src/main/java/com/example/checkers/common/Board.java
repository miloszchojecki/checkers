package com.example.checkers.common;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

/**
 * class that represents board
 */
public class Board {
    private final GridPane gridPane;
    private final Tile[][] tiles;
    /**
     * Board constructor
     */
    public Board() {
        gridPane = new GridPane();
        tiles = new Tile[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile(i, j);
                gridPane.add(tiles[i][j].getStackPane(), i, j);
                tiles[i][j].setPlayable((i + j) % 2 == 1);
            }
        }
    }
    /**
     * Method that gets GridPane
     */
    public GridPane getGridPane() {
        return gridPane;
    }
    /**
     * Method that gets Tiles
     */
    public Tile[][] getTiles() {
        return tiles;
    }
    /**
     * Method that gets Neighbor of the tile
     * @param offsetX offset of value x
     * @param offsetY offset of value y
     */
    public Tile getNeighbor(Tile tile, int offsetX, int offsetY){
        Tile neighbor = null;
        try{
            neighbor = tiles[tile.getX() + offsetX][tile.getY() + offsetY];
        }catch(Exception ignored){}
        return neighbor;
    }
}
