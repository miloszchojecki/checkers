package com.example.checkers;

import com.example.checkers.GameLogic;
import com.example.checkers.PieceColor;
import com.example.checkers.Tile;

import java.util.ArrayList;

public class EnglishCheckersGameLogic extends GameLogic {
    @Override
    public void initialize() {

    }

    @Override
    public ArrayList<Tile> getPossibleMoves(Tile tile) {
        return null;
    }

    @Override
    public void makeMove(Tile from, Tile to) {

    }

    @Override
    public boolean checkWinner() {
        return false;
    }

    @Override
    public PieceColor getWinner() {
        return null;
    }
}
