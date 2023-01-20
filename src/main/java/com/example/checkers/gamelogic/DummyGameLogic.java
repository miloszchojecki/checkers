package com.example.checkers.gamelogic;

import com.example.checkers.common.*;

import java.util.ArrayList;
/**
 * class of dummyGameLogic that extends GameLogic
 */
public class DummyGameLogic extends GameLogic
{


    private int totalMoves = 0;
    private final int maximumMoves = 10;

    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;

    public DummyGameLogic(Board board)
    {
        this.gameBoard = board;
        tiles = gameBoard.getTiles();
        turn = PieceColor.WHITE;
    }

    @Override
    public void initialize()
    {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
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
                    tiles[i][j].getPiece().makeQueen();
                    blackPieces.add(newPiece);
                }

                if (j >= 5 && tiles[i][j].isPlayable())
                {
                    Piece newPiece = new Piece(PieceColor.WHITE);
                    tiles[i][j].placePiece(newPiece);
                    tiles[i][j].getPiece().makeQueen();
                    whitePieces.add(newPiece);
                }
            }
    }

    public ArrayList<TileCoordinates> getPossibleMoves(TileCoordinates tileCoordinates)
    {
        Tile tile = coordinatesToTile(tileCoordinates);
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
        return tilesToCoordinates(possibleMoves);
    }


    public MoveInfo makeMove(TileCoordinates fromCoordinates, TileCoordinates toCoordinates)
    {
        //if(!getPossibleMoves(fromCoordinates).contains(toCoordinates))
        //    return null;

        Tile from = coordinatesToTile(fromCoordinates);
        Tile to = coordinatesToTile(toCoordinates);

        Piece piece = from.getPiece();
        from.removePiece();
        to.placePiece(piece);
        totalMoves++;
        if (totalMoves >= maximumMoves)
        {
            winner = turn;
        }
        changeTurn();
        return new MoveInfo(to.getPieceColor(), turn, null, true, winner);
    }

}
