package com.example.checkers;

import java.util.ArrayList;

public class DummyGameLogic extends GameLogic
{
    private Board gameBoard;
    private Tile[][] tiles;

    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;


    public DummyGameLogic(Board board)
    {
        this.gameBoard = board;
        tiles = gameBoard.getTiles();
    }

    @Override
    public void initialize()
    {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        for(int i=0; i<tiles.length; i++)
            for (int j=0; j<tiles[0].length; j++)
            {
                if(j<=2 && tiles[i][j].isPlayable())
                {
                    Piece newPiece = new Piece(PieceColor.BLACK);
                    tiles[i][j].placePiece(newPiece);
                    blackPieces.add(newPiece);
                }

                if(j>=5 && tiles[i][j].isPlayable())
                {
                    Piece newPiece = new Piece(PieceColor.WHITE);
                    tiles[i][j].placePiece(newPiece);
                    whitePieces.add(newPiece);
                }
            }
    }

}
