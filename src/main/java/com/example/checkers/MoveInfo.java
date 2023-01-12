package com.example.checkers;

public class MoveInfo
{

    private PieceColor pieceColor, nextTurn, winner;
    private TileCoordinates killedPiece;
    private boolean queen;

    public MoveInfo(PieceColor pieceColor, PieceColor nextTurn, TileCoordinates killedPiece, boolean queen, PieceColor winner)
    {
        this.pieceColor = pieceColor;
        this.nextTurn = nextTurn;
        this.killedPiece = killedPiece;
        this.queen = queen;
        this.winner = winner;
    }

    public PieceColor getPieceColor()
    {
        return pieceColor;
    }

    public PieceColor getNextTurn()
    {
        return nextTurn;
    }

    public TileCoordinates getKilledPiece()
    {
        return killedPiece;
    }

    public boolean isQueen()
    {
        return queen;
    }

    public PieceColor getWinner()
    {
        return winner;
    }
}
