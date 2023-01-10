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
        if (possibleMoves.get(0).hasPiece()) {
            if (gameBoard.getNeighbor(possibleMoves.get(0), -1, -1) != null &&
                    !gameBoard.getNeighbor(possibleMoves.get(0), -1, -1).hasPiece()) {
                possibleMoves.set(0, gameBoard.getNeighbor(possibleMoves.get(0), -1, -1));
            }
            else possibleMoves.set(0, tile);
        }
        if (possibleMoves.get(1).hasPiece()) {
            if (gameBoard.getNeighbor(possibleMoves.get(1), -1, +1) != null &&
                    !gameBoard.getNeighbor(possibleMoves.get(1), -1, +1).hasPiece()) {
                possibleMoves.set(1, gameBoard.getNeighbor(possibleMoves.get(1), -1, +1));
            }
            else possibleMoves.set(1, tile);
        }
        if (possibleMoves.get(2).hasPiece()) {
            if (gameBoard.getNeighbor(possibleMoves.get(2), +1, -1) != null &&
                    !gameBoard.getNeighbor(possibleMoves.get(2), +1, -1).hasPiece()) {
                possibleMoves.set(2, gameBoard.getNeighbor(possibleMoves.get(2), +1, -1));
            }
            else possibleMoves.set(2, tile);
        }
        if (possibleMoves.get(3).hasPiece()) {
            if (gameBoard.getNeighbor(possibleMoves.get(3), +1, +1) != null &&
                    !gameBoard.getNeighbor(possibleMoves.get(3), +1, +1).hasPiece()) {
                possibleMoves.set(3, gameBoard.getNeighbor(possibleMoves.get(3), +1, +1));
            }
            else possibleMoves.set(3, tile);
        }
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
