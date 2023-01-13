package com.example.checkers.common;

public class MoveInfo
{

    private final PieceColor pieceColor;
    private final PieceColor nextTurn;
    private final PieceColor winner;
    private final TileCoordinates killedPiece;
    private final boolean queen;

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
