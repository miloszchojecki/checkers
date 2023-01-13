package com.example.checkers.common;

/**
 * class with info about moves
 */
public class MoveInfo
{
    private final PieceColor pieceColor;
    private final PieceColor nextTurn;
    private final PieceColor winner;
    private final TileCoordinates killedPiece;
    private final boolean queen;
    /**
     * MoveInfo constructor
     */
    public MoveInfo(PieceColor pieceColor, PieceColor nextTurn, TileCoordinates killedPiece, boolean queen, PieceColor winner)
    {
        this.pieceColor = pieceColor;
        this.nextTurn = nextTurn;
        this.killedPiece = killedPiece;
        this.queen = queen;
        this.winner = winner;
    }
    /**
     * Method that gets PieceColor
     */
    public PieceColor getPieceColor()
    {
        return pieceColor;
    }
    /**
     * Method that gets NextTurn
     */
    public PieceColor getNextTurn()
    {
        return nextTurn;
    }
    /**
     * Method that gets KilledPiece
     */
    public TileCoordinates getKilledPiece()
    {
        return killedPiece;
    }
    /**
     * Method that checks if Piece is Queen
     */
    public boolean isQueen()
    {
        return queen;
    }
    /**
     * Method that gets Winner
     */
    public PieceColor getWinner()
    {
        return winner;
    }
}
