package com.example.checkers.gamelogic;

import com.example.checkers.common.*;

import java.util.ArrayList;
/**
 * class of GameLogic
 */
public abstract class GameLogic {
    /**
     * creating gameBoard
     */
    protected Board gameBoard;
    /**
     * creating table of tiles
     */
    protected Tile[][] tiles;
    /**
     * assigning turn
     */
    protected PieceColor turn = PieceColor.WHITE;
    /**
     * assigning winner
     */
    protected PieceColor winner = null;
    /**
     * Method that changes turn
     */
    protected void changeTurn() {
        if (turn == PieceColor.BLACK)
            turn = PieceColor.WHITE;
        else
            turn = PieceColor.BLACK;
    }
    /**
     * Method that changes coordinates to tile
     */
    protected Tile coordinatesToTile(TileCoordinates coordinates) {
        return tiles[coordinates.getX()][coordinates.getY()];
    }
    /**
     * Method that changes tiles to coordinates
     */
    protected ArrayList<TileCoordinates> tilesToCoordinates(ArrayList<Tile> tiles) {
        ArrayList<TileCoordinates> tileCoordinates = new ArrayList<>();
        for (Tile tile : tiles) {
            tileCoordinates.add(new TileCoordinates(tile));
        }
        return tileCoordinates;
    }
    /**
     * Method that gets turn
     */
    public PieceColor getTurn() {
        return turn;
    }
    /**
     * Initialize method
     */
    public abstract void initialize();
    /**
     * Method that gets possible moves
     */
    public abstract ArrayList<TileCoordinates> getPossibleMoves(TileCoordinates tile);
    /**
     * Method that makes move
     */
    public abstract MoveInfo makeMove(TileCoordinates from, TileCoordinates to);
    /**
     * Method that checkes if kill is possible
     */
    public abstract boolean isKillPossible(TileCoordinates tile);
    /**
     * Method that gets tiles
     */
    public Tile[][] getTiles() {
        return tiles;
    }

}
