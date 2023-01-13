package com.example.checkers.gamelogic;

import com.example.checkers.common.*;

import java.util.ArrayList;

public class TwoLineCheckersGameLogic extends GameLogic {
    private int totalMoves = 0;
    private final int maximumMoves = 100;

    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;

    private PieceColor winner = null;


    public TwoLineCheckersGameLogic(Board board) {
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
                if (j <= 1 && tiles[i][j].isPlayable()) {
                    Piece newPiece = new Piece(PieceColor.BLACK);
                    tiles[i][j].placePiece(newPiece);
                    blackPieces.add(newPiece);
                }

                if (j >= 6 && tiles[i][j].isPlayable()) {
                    Piece newPiece = new Piece(PieceColor.WHITE);
                    tiles[i][j].placePiece(newPiece);
                    whitePieces.add(newPiece);
                }
            }
    }

    @Override
    public ArrayList<TileCoordinates> getPossibleMoves(TileCoordinates tile) {
        if (coordinatesToTile(tile).getPiece().isQueen()) {
            boolean possibleKill = false;
            ArrayList<Tile> possibleMoves = new ArrayList<>();
            Tile thisTile = coordinatesToTile(tile);
            try {
                Tile current = tiles[tile.getX() - 1][tile.getY() - 1];
                if (current.hasPiece()) {
                    if (gameBoard.getNeighbor(current, -1, -1) != null &&
                            !gameBoard.getNeighbor(current, -1, -1).hasPiece() &&
                            current.getPieceColor() != thisTile.getPieceColor()) {
                        possibleMoves.add(gameBoard.getNeighbor(current, -1, -1));
                        possibleKill = true;
                    }
                }
            } catch (Exception ignored) {
            }
            try {
                Tile current = tiles[tile.getX() - 1][tile.getY() + 1];
                if (current.hasPiece()) {
                    if (gameBoard.getNeighbor(current, -1, +1) != null &&
                            !gameBoard.getNeighbor(current, -1, +1).hasPiece() &&
                            current.getPieceColor() != thisTile.getPieceColor()) {
                        possibleMoves.add(gameBoard.getNeighbor(current, -1, +1));
                        possibleKill = true;
                    }
                }
            } catch (Exception ignored) {
            }
            try {
                Tile current = tiles[tile.getX() + 1][tile.getY() - 1];
                if (current.hasPiece()) {
                    if (gameBoard.getNeighbor(current, +1, -1) != null &&
                            !gameBoard.getNeighbor(current, +1, -1).hasPiece() &&
                            current.getPieceColor() != thisTile.getPieceColor()) {
                        possibleMoves.add(gameBoard.getNeighbor(current, +1, -1));
                        possibleKill = true;
                    }
                }
            } catch (Exception ignored) {
            }
            try {
                Tile current = tiles[tile.getX() + 1][tile.getY() + 1];
                if (current.hasPiece()) {
                    if (gameBoard.getNeighbor(current, +1, +1) != null &&
                            !gameBoard.getNeighbor(current, +1, +1).hasPiece() &&
                            current.getPieceColor() != thisTile.getPieceColor()) {
                        possibleMoves.add(gameBoard.getNeighbor(current, +1, +1));
                        possibleKill = true;
                    }
                }
            } catch (Exception ignored) {
            }
            if (!possibleKill) {
                try {
                    possibleMoves.add(tiles[tile.getX() - 1][tile.getY() - 1]);
                } catch (Exception ignored) {
                }
                try {
                    possibleMoves.add(tiles[tile.getX() + 1][tile.getY() - 1]);
                } catch (Exception ignored) {
                }
                try {
                    possibleMoves.add(tiles[tile.getX() + 1][tile.getY() + 1]);
                } catch (Exception ignored) {
                }
                try {
                    possibleMoves.add(tiles[tile.getX() - 1][tile.getY() + 1]);
                } catch (Exception ignored) {
                }
                possibleMoves.removeIf(Tile::hasPiece);
            }
            return tilesToCoordinates(possibleMoves);
        } else {
            boolean possibleKill = false;
            ArrayList<Tile> possibleMoves = new ArrayList<>();
            Tile thisTile = coordinatesToTile(tile);
            if (thisTile.getPieceColor() == PieceColor.BLACK) {
                try {
                    Tile current = tiles[tile.getX() - 1][tile.getY() + 1];
                    if (current.hasPiece()) {
                        if (gameBoard.getNeighbor(current, -1, +1) != null &&
                                !gameBoard.getNeighbor(current, -1, +1).hasPiece() &&
                                current.getPieceColor() != thisTile.getPieceColor()) {
                            possibleMoves.add(gameBoard.getNeighbor(current, -1, +1));
                            possibleKill = true;
                        }
                    }
                } catch (Exception ignored) {
                }
                try {
                    Tile current = tiles[tile.getX() + 1][tile.getY() + 1];
                    if (current.hasPiece()) {
                        if (gameBoard.getNeighbor(current, +1, +1) != null &&
                                !gameBoard.getNeighbor(current, +1, +1).hasPiece() &&
                                current.getPieceColor() != thisTile.getPieceColor()) {
                            possibleMoves.add(gameBoard.getNeighbor(current, +1, +1));
                            possibleKill = true;
                        }
                    }
                } catch (Exception ignored) {
                }
            } else {
                try {
                    Tile current = tiles[tile.getX() - 1][tile.getY() - 1];
                    if (current.hasPiece()) {
                        if (gameBoard.getNeighbor(current, -1, -1) != null &&
                                !gameBoard.getNeighbor(current, -1, -1).hasPiece() &&
                                current.getPieceColor() != thisTile.getPieceColor()) {
                            possibleMoves.add(gameBoard.getNeighbor(current, -1, -1));
                            possibleKill = true;
                        }
                    }
                } catch (Exception ignored) {
                }

                try {
                    Tile current = tiles[tile.getX() + 1][tile.getY() - 1];
                    if (current.hasPiece()) {
                        if (gameBoard.getNeighbor(current, +1, -1) != null &&
                                !gameBoard.getNeighbor(current, +1, -1).hasPiece() &&
                                current.getPieceColor() != thisTile.getPieceColor()) {
                            possibleMoves.add(gameBoard.getNeighbor(current, +1, -1));
                            possibleKill = true;
                        }
                    }
                } catch (Exception ignored) {
                }
            }
            if (!possibleKill) {
                if (thisTile.getPieceColor() == PieceColor.WHITE) {
                    try {
                        possibleMoves.add(tiles[tile.getX() - 1][tile.getY() - 1]);
                    } catch (Exception ignored) {
                    }
                    try {
                        possibleMoves.add(tiles[tile.getX() + 1][tile.getY() - 1]);
                    } catch (Exception ignored) {
                    }
                } else {
                    try {
                        possibleMoves.add(tiles[tile.getX() + 1][tile.getY() + 1]);
                    } catch (Exception ignored) {
                    }
                    try {
                        possibleMoves.add(tiles[tile.getX() - 1][tile.getY() + 1]);
                    } catch (Exception ignored) {
                    }
                }
                possibleMoves.removeIf(Tile::hasPiece);
            }
            return tilesToCoordinates(possibleMoves);
        }
    }


    @Override
    public MoveInfo makeMove(TileCoordinates fromCoordinates, TileCoordinates toCoordinates) {
        //if (!getPossibleMoves(fromCoordinates).contains(toCoordinates))
        //    return null;
        Tile from = coordinatesToTile(fromCoordinates);
        Tile to = coordinatesToTile(toCoordinates);
        TileCoordinates killed = null;
        boolean queen = false;
        Piece piece = tiles[from.getX()][from.getY()].getPiece();
        if (piece.isQueen()) {
            queen = true;
        }
        boolean kill = false;
        if (Math.abs(from.getX() - to.getX()) > 1) {
            tiles[(from.getX() + to.getX()) / 2][(from.getY() + to.getY()) / 2].removePiece();
            killed = new TileCoordinates(tiles[(from.getX() + to.getX()) / 2][(from.getY() + to.getY()) / 2]);
            kill = isKillPossible(toCoordinates);
        }
        from.removePiece();
        to.placePiece(piece);
        if (queen) {
            to.getPiece().makeQueen();
        }
        if (to.getPieceColor() == PieceColor.WHITE) {
            if (to.getY() == 0) {
                to.getPiece().makeQueen();
                queen = true;
            }
        } else {
            if (to.getY() == 7) {
                to.getPiece().makeQueen();
                queen = true;
            }
        }
        totalMoves++;
        if (totalMoves >= maximumMoves) {
            winner = to.getPieceColor();
        }
        if (!kill) {
            changeTurn();
        }
        return new MoveInfo(to.getPieceColor(), turn, killed, queen, winner);
    }

    @Override
    public boolean isKillPossible(TileCoordinates tile) {
        boolean possibleKill = false;
        Tile thisTile = coordinatesToTile(tile);
        try {
            Tile current = tiles[tile.getX() - 1][tile.getY() - 1];
            if (current.hasPiece()) {
                if (gameBoard.getNeighbor(current, -1, -1) != null &&
                        !gameBoard.getNeighbor(current, -1, -1).hasPiece() &&
                        current.getPieceColor() != thisTile.getPieceColor()) {

                    possibleKill = true;
                }
            }
        } catch (Exception ignored) {
        }
        try {
            Tile current = tiles[tile.getX() - 1][tile.getY() + 1];
            if (current.hasPiece()) {
                if (gameBoard.getNeighbor(current, -1, +1) != null &&
                        !gameBoard.getNeighbor(current, -1, +1).hasPiece() &&
                        current.getPieceColor() != thisTile.getPieceColor()) {

                    possibleKill = true;
                }
            }
        } catch (Exception ignored) {
        }
        try {
            Tile current = tiles[tile.getX() + 1][tile.getY() - 1];
            if (current.hasPiece()) {
                if (gameBoard.getNeighbor(current, +1, -1) != null &&
                        !gameBoard.getNeighbor(current, +1, -1).hasPiece() &&
                        current.getPieceColor() != thisTile.getPieceColor()) {

                    possibleKill = true;
                }
            }
        } catch (Exception ignored) {
        }
        try {
            Tile current = tiles[tile.getX() + 1][tile.getY() + 1];
            if (current.hasPiece()) {
                if (gameBoard.getNeighbor(current, +1, +1) != null &&
                        !gameBoard.getNeighbor(current, +1, +1).hasPiece() &&
                        current.getPieceColor() != thisTile.getPieceColor()) {

                    possibleKill = true;
                }
            }
        } catch (Exception ignored) {
        }
        return possibleKill;
    }
}
