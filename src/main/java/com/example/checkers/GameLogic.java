package com.example.checkers;

import java.util.ArrayList;

public abstract class GameLogic
{
    public abstract void initialize();

    public abstract ArrayList<Tile> getPossibleMoves(Tile tile);

    public abstract void makeMove(Tile from, Tile to);

    public abstract boolean checkWinner();

    public abstract PieceColor getWinner();
}
