package com.example.checkers.common;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Board {
    private final GridPane gridPane;

    private final Tile[][] tiles;

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

    public GridPane getGridPane() {
        return gridPane;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getNeighbor(Tile tile, int offsetX, int offsetY){
        Tile neighbor = null;
        try{
            neighbor = tiles[tile.getX() + offsetX][tile.getY() + offsetY];
        }catch(Exception ignored){}
        return neighbor;
    }

    public ArrayList<Tile> getTileNeighbors(Tile tile) {
        int tileX = tile.getX();
        int tileY = tile.getY();
        ArrayList<Tile> neighbors = new ArrayList<>();
        try {
            neighbors.add(tiles[tileX - 1][tileY - 1]);
        } catch (Exception ignored) {
        }
        try {
            neighbors.add(tiles[tileX - 1][tileY + 1]);
        } catch (Exception ignored) {
        }
        try {
            neighbors.add(tiles[tileX + 1][tileY - 1]);
        } catch (Exception ignored) {
        }
        try {
            neighbors.add(tiles[tileX + 1][tileY + 1]);
        } catch (Exception ignored) {
        }
        return neighbors;
    }
}
