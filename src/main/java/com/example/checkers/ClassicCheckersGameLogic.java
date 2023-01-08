package com.example.checkers;

import java.util.ArrayList;

public class ClassicCheckersGameLogic extends GameLogic{

    private Board gameBoard;
    private Tile[][] tiles;

    private int totalMoves = 0;
    private final int maximumMoves = 10;

    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;

    private PieceColor winner = null;

    public ClassicCheckersGameLogic(Board board)
    {
        this.gameBoard = board;
        tiles = gameBoard.getTiles();
    }
    @Override
    public void initialize() {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        for (Tile[] tile : tiles)
            for (int j = 0; j < tiles[0].length; j++) {
                if (tile[j].hasPiece()) {
                    tile[j].removePiece();
                }
                if (j <= 2 && tile[j].isPlayable()) {
                    Piece newPiece = new Piece(PieceColor.BLACK);
                    tile[j].placePiece(newPiece);
                    blackPieces.add(newPiece);
                }

                if (j >= 5 && tile[j].isPlayable()) {
                    Piece newPiece = new Piece(PieceColor.WHITE);
                    tile[j].placePiece(newPiece);
                    whitePieces.add(newPiece);
                }
            }
    }

    @Override
    public ArrayList<Tile> getPossibleMoves(Tile tile) {
        int tileX = tile.getX();
        int tileY = tile.getY();
        ArrayList<Tile> possibleMoves = gameBoard.getTileNeighbors(tile);
        return possibleMoves;
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
