package com.example.checkers;

import java.util.ArrayList;

public class ClassicCheckersGameLogic extends GameLogic {

    private Board gameBoard;
    private Tile[][] tiles;

    private int totalMoves = 0;
    private final int maximumMoves = 100;

    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;

    private PieceColor winner = null;


    public ClassicCheckersGameLogic(Board board) {
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
        int tileX = tile.getX();
        int tileY = tile.getY();
        boolean possibleKill = false;
        ArrayList<Tile> possibleMoves = new ArrayList<>();
        try {
            Tile current = tiles[tileX - 1][tileY - 1];
            if (current.hasPiece()) {
                if (gameBoard.getNeighbor(current, -1, -1) != null &&
                        !gameBoard.getNeighbor(current, -1, -1).hasPiece() &&
                        current.getPieceColor() != tile.getPieceColor()) {
                    possibleMoves.add(gameBoard.getNeighbor(current, -1, -1));
                    possibleKill = true;
                }
            }
        } catch (Exception ignored) {
        }
        try {
            Tile current = tiles[tileX - 1][tileY + 1];
            if (current.hasPiece()) {
                if (gameBoard.getNeighbor(current, -1, +1) != null &&
                        !gameBoard.getNeighbor(current, -1, +1).hasPiece() &&
                        current.getPieceColor() != tile.getPieceColor()) {
                    possibleMoves.add(gameBoard.getNeighbor(current, -1, +1));
                    possibleKill = true;
                }
            }
        } catch (Exception ignored) {
        }
        try {
            Tile current = tiles[tileX + 1][tileY - 1];
            if (current.hasPiece()) {
                if (gameBoard.getNeighbor(current, +1, -1) != null &&
                        !gameBoard.getNeighbor(current, +1, -1).hasPiece() &&
                        current.getPieceColor() != tile.getPieceColor()) {
                    possibleMoves.add(gameBoard.getNeighbor(current, +1, -1));
                    possibleKill = true;
                }
            }
        } catch (Exception ignored) {
        }
        try {
            Tile current = tiles[tileX + 1][tileY + 1];
            if (current.hasPiece()) {
                if (gameBoard.getNeighbor(current, +1, +1) != null &&
                        !gameBoard.getNeighbor(current, +1, +1).hasPiece() &&
                        current.getPieceColor() != tile.getPieceColor()) {
                    possibleMoves.add(gameBoard.getNeighbor(current, +1, +1));
                    possibleKill = true;
                }
            }
        } catch (Exception ignored) {
        }
        if (!possibleKill) {
            if (tile.getPieceColor() == PieceColor.WHITE) {
                try {
                    possibleMoves.add(tiles[tileX - 1][tileY - 1]);
                } catch (Exception ignored) {
                }
                try {
                    possibleMoves.add(tiles[tileX + 1][tileY - 1]);
                } catch (Exception ignored) {
                }
            } else {
                try {
                    possibleMoves.add(tiles[tileX + 1][tileY + 1]);
                } catch (Exception ignored) {
                }
                try {
                    possibleMoves.add(tiles[tileX - 1][tileY + 1]);
                } catch (Exception ignored) {
                }
            }
            possibleMoves.removeIf(Tile::hasPiece);
        }
        return possibleMoves;
    }

    public void makeMove(Tile from, Tile to) {
        Piece piece = from.getPiece();
        if (Math.abs(from.getX() - to.getX()) > 1) {
            tiles[(from.getX() + to.getX()) / 2][(from.getY() + to.getY()) / 2].removePiece();

        }
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
