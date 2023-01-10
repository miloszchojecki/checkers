package com.example.checkers;

import java.util.ArrayList;

public class DummyGameLogic extends GameLogic {
    private Board gameBoard;
    private Tile[][] tiles;

    private int totalMoves = 0;
    private final int maximumMoves = 10;

    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;

    private PieceColor winner = null;


    public DummyGameLogic(Board board) {
        this.gameBoard = board;
        tiles = gameBoard.getTiles();
    }

    @Override
    public void initialize() {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].hasPiece()) {
                    tiles[i][j].removePiece();
                }
                if (j <= 2 && tiles[i][j].isPlayable()) {
                    Piece newPiece = new Piece(PieceColor.BLACK);
                    tiles[i][j].placePiece(newPiece);
                    blackPieces.add(newPiece);
                }

                if (j >= 5 && tiles[i][j].isPlayable()) {
                    Piece newPiece = new Piece(PieceColor.WHITE);
                    tiles[i][j].placePiece(newPiece);
                    whitePieces.add(newPiece);
                }
            }
    }

    public ArrayList<Tile> getPossibleMoves(Tile tile) {
        /*
        int tileX = tile.getX();
        int tileY = tile.getY();
        ArrayList<Tile> possibleMoves = new ArrayList<>();
        try
        {
            possibleMoves.add(tiles[tileX - 1][tileY - 1]);
        }
        catch (Exception ignored)
        {
        }
        try
        {
            possibleMoves.add(tiles[tileX - 1][tileY + 1]);
        }
        catch (Exception ignored)
        {
        }
        try
        {
            possibleMoves.add(tiles[tileX + 1][tileY - 1]);
        }
        catch (Exception ignored)
        {
        }
        try
        {
            possibleMoves.add(tiles[tileX + 1][tileY + 1]);
        }
        catch (Exception ignored)
        {
        }
        possibleMoves.removeIf(Tile::hasPiece);
        return possibleMoves;

        */
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

    public void makeMove(Tile from, Tile to) {
        Piece piece = from.getPiece();
        from.removePiece();
        to.placePiece(piece);
        totalMoves++;
        if (totalMoves >= maximumMoves) {
            winner = to.getPieceColor();
        }
    }

    public boolean checkWinner() {
        return winner != null;
    }

    public PieceColor getWinner() {
        return winner;
    }
}
