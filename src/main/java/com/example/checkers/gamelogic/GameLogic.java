package com.example.checkers.gamelogic;

import com.example.checkers.common.*;

import java.util.ArrayList;
public abstract class GameLogic {

    protected Board gameBoard;
    protected Tile[][] tiles;
    protected PieceColor turn = PieceColor.WHITE;
    protected PieceColor winner = null;
    protected void changeTurn() {
        if (turn == PieceColor.BLACK)
            turn = PieceColor.WHITE;
        else
            turn = PieceColor.BLACK;
    }

    protected Tile coordinatesToTile(TileCoordinates coordinates) {
        return tiles[coordinates.getX()][coordinates.getY()];
    }

    protected ArrayList<TileCoordinates> tilesToCoordinates(ArrayList<Tile> tiles) {
        ArrayList<TileCoordinates> tileCoordinates = new ArrayList<>();
        for (Tile tile : tiles) {
            tileCoordinates.add(new TileCoordinates(tile));
        }
        return tileCoordinates;
    }

    protected int moveDir(PieceColor pieceColor)
    {
        return (pieceColor == PieceColor.WHITE ? -1 : 1);
    }
    public PieceColor getTurn() {
        return turn;
    }
    public abstract void initialize();
    public abstract ArrayList<TileCoordinates> getPossibleMoves(TileCoordinates tile);
    public abstract MoveInfo makeMove(TileCoordinates from, TileCoordinates to);
    public Tile[][] getTiles() {
        return tiles;
    }

}
