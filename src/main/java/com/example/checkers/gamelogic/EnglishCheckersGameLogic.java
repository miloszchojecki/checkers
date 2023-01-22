package com.example.checkers.gamelogic;

import com.example.checkers.common.*;

import java.util.ArrayList;

public class EnglishCheckersGameLogic extends GameLogic
{

    private ArrayList<TileCoordinates> blackCoordinates;
    private ArrayList<TileCoordinates> whiteCoordinates;

    public EnglishCheckersGameLogic(Board board)
    {
        this.gameBoard = board;
        tiles = gameBoard.getTiles();
    }

    private void updateCoordinates()
    {
        blackCoordinates = new ArrayList<>();
        whiteCoordinates = new ArrayList<>();
        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[0].length; j++)
            {
                if (tiles[i][j].hasPiece())
                {
                    PieceColor pieceColor = tiles[i][j].getPieceColor();
                    if (pieceColor == PieceColor.WHITE)
                        whiteCoordinates.add(new TileCoordinates(tiles[i][j]));
                    else
                        blackCoordinates.add(new TileCoordinates(tiles[i][j]));
                }
            }
    }

    @Override
    public void initialize()
    {
        whiteCoordinates = new ArrayList<>();
        blackCoordinates = new ArrayList<>();
        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[0].length; j++)
            {
                if (tiles[i][j].hasPiece())
                {
                    tiles[i][j].removePiece();
                }
                if (j <= 2 && tiles[i][j].isPlayable())
                {
                    Piece newPiece = new Piece(PieceColor.BLACK);
                    tiles[i][j].placePiece(newPiece);
                    blackCoordinates.add(new TileCoordinates(i, j));
                }

                if (j >= 5 && tiles[i][j].isPlayable())
                {
                    Piece newPiece = new Piece(PieceColor.WHITE);
                    tiles[i][j].placePiece(newPiece);
                    whiteCoordinates.add(new TileCoordinates(i, j));
                }
            }
    }

    private ArrayList<TileCoordinates> getPossibleKills(TileCoordinates tileCoordinates, boolean isQueen)
    {
        ArrayList<TileCoordinates> possibleKills = new ArrayList<>();
        Tile tile = coordinatesToTile(tileCoordinates);
        int moveDir = moveDir(turn);
        if (gameBoard.getNeighbor(tile, -1, moveDir) != null && gameBoard.getNeighbor(tile, -2, moveDir * 2) != null)
        {
            if (gameBoard.getNeighbor(tile, -1, moveDir).hasPiece())
                if (gameBoard.getNeighbor(tile, -1, moveDir).getPieceColor() != turn && !gameBoard.getNeighbor(tile, -2, moveDir * 2).hasPiece())
                {
                    possibleKills.add(new TileCoordinates(gameBoard.getNeighbor(tile, -2, moveDir * 2)));
                }
        }
        if (gameBoard.getNeighbor(tile, 1, moveDir) != null && gameBoard.getNeighbor(tile, 2, moveDir * 2) != null)
        {
            if (gameBoard.getNeighbor(tile, 1, moveDir).hasPiece())
                if (gameBoard.getNeighbor(tile, 1, moveDir).getPieceColor() != turn && !gameBoard.getNeighbor(tile, 2, moveDir * 2).hasPiece())
                {
                    possibleKills.add(new TileCoordinates(gameBoard.getNeighbor(tile, 2, moveDir * 2)));
                }
        }
        if (!isQueen)
            return possibleKills;

        if (gameBoard.getNeighbor(tile, -1, -moveDir) != null && gameBoard.getNeighbor(tile, -2, -moveDir * 2) != null)
        {
            if (gameBoard.getNeighbor(tile, -1, -moveDir).hasPiece())
                if (gameBoard.getNeighbor(tile, -1, -moveDir).getPieceColor() != turn && !gameBoard.getNeighbor(tile, -2, -moveDir * 2).hasPiece())
                {
                    possibleKills.add(new TileCoordinates(gameBoard.getNeighbor(tile, -2, -moveDir * 2)));
                }
        }
        if (gameBoard.getNeighbor(tile, 1, -moveDir) != null && gameBoard.getNeighbor(tile, 2, -moveDir * 2) != null)
        {
            if (gameBoard.getNeighbor(tile, 1, -moveDir).hasPiece())
                if (gameBoard.getNeighbor(tile, 1, -moveDir).getPieceColor() != turn && !gameBoard.getNeighbor(tile, 2, -moveDir * 2).hasPiece())
                {
                    possibleKills.add(new TileCoordinates(gameBoard.getNeighbor(tile, 2, -moveDir * 2)));
                }
        }
        return possibleKills;
    }

    @Override
    public ArrayList<TileCoordinates> getPossibleMoves(TileCoordinates tile)
    {
        ArrayList<TileCoordinates> playerCoordinates;
        if (turn == PieceColor.WHITE)
            playerCoordinates = whiteCoordinates;
        else
            playerCoordinates = blackCoordinates;

        ArrayList<TileCoordinates> killPossible = new ArrayList<>();
        for (TileCoordinates coordinates : playerCoordinates)
        {
            if (getPossibleKills(coordinates, coordinatesToTile(coordinates).getPiece().isQueen()).size() > 0)
                killPossible.add(coordinates);
        }

        ArrayList<TileCoordinates> possibleMoves = new ArrayList<>();
        if (killPossible.isEmpty())
        {
            if (gameBoard.getNeighbor(coordinatesToTile(tile), -1, moveDir(turn)) != null)
                if (!gameBoard.getNeighbor(coordinatesToTile(tile), -1, moveDir(turn)).hasPiece())
                    possibleMoves.add(new TileCoordinates(gameBoard.getNeighbor(coordinatesToTile(tile), -1, moveDir(turn))));
            if (gameBoard.getNeighbor(coordinatesToTile(tile), 1, moveDir(turn)) != null)
                if (!gameBoard.getNeighbor(coordinatesToTile(tile), 1, moveDir(turn)).hasPiece())
                    possibleMoves.add(new TileCoordinates(gameBoard.getNeighbor(coordinatesToTile(tile), 1, moveDir(turn))));
            if(!coordinatesToTile(tile).getPiece().isQueen())
                return possibleMoves;
            if (gameBoard.getNeighbor(coordinatesToTile(tile), -1, -moveDir(turn)) != null)
                if (!gameBoard.getNeighbor(coordinatesToTile(tile), -1, -moveDir(turn)).hasPiece())
                    possibleMoves.add(new TileCoordinates(gameBoard.getNeighbor(coordinatesToTile(tile), -1, -moveDir(turn))));
            if (gameBoard.getNeighbor(coordinatesToTile(tile), 1, -moveDir(turn)) != null)
                if (!gameBoard.getNeighbor(coordinatesToTile(tile), 1, -moveDir(turn)).hasPiece())
                    possibleMoves.add(new TileCoordinates(gameBoard.getNeighbor(coordinatesToTile(tile), 1, -moveDir(turn))));
        }
        for (TileCoordinates coordinates : killPossible)
        {
            if (tile.equals(coordinates))
            {
                return getPossibleKills(tile, coordinatesToTile(tile).getPiece().isQueen());
            }
        }
        return possibleMoves;
    }

    @Override
    public MoveInfo makeMove(TileCoordinates from, TileCoordinates to)
    {
        //check if move is possible
        ArrayList<TileCoordinates> possibleMoves = getPossibleMoves(from);
        boolean isPossible = false;
        for (TileCoordinates possible : possibleMoves)
        {
            if (possible.equals(to))
                isPossible = true;
        }
        if (!isPossible)
            return null;

        PieceColor oldTurn = turn;
        Tile fromTile = coordinatesToTile(from);
        Tile toTile = coordinatesToTile(to);
        boolean queen = fromTile.getPiece().isQueen();
        fromTile.removePiece();
        toTile.placePiece(new Piece(turn));
        if (queen)
            toTile.getPiece().makeQueen();
        TileCoordinates killedPiece = null;
        if (Math.abs(from.getX() - to.getX()) > 1)
        {
            killedPiece = new TileCoordinates(from.getX() + (to.getX() - from.getX()) / 2, from.getY() + (to.getY() - from.getY()) / 2);
            coordinatesToTile(killedPiece).removePiece();
        }
        if ((to.getY() == 0 && turn == PieceColor.WHITE) || (to.getY() == 7 && turn == PieceColor.BLACK))
        {
            toTile.getPiece().makeQueen();
            queen = true;
            changeTurn();
            updateCoordinates();
            return new MoveInfo(oldTurn, turn, killedPiece, queen, winner);
        }
        if (!(killedPiece != null && getPossibleKills(to, queen).size() > 0))
            changeTurn();
        updateCoordinates();
        if (whiteCoordinates.size() == 0)
            winner = PieceColor.BLACK;
        else if (blackCoordinates.size() == 0)
            winner = PieceColor.WHITE;
        return new MoveInfo(oldTurn, turn, killedPiece, queen, winner);
    }
}
