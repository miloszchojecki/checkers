package com.example.checkers;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ClientGameHandler
{
    private Board board;
    private Tile[][] tiles;
    private Label label;
    private Tile selected = null;
        private boolean clickPossible = false;
        private ClientCommunicator clientCommunicator;

    private ArrayList<TileCoordinates> possibleMoves;
    private ArrayList<Tile> possibleMovesTiles;

        PieceColor pieceColor;
        char pieceColorChar;

        private Tile tileFromCoordinates(TileCoordinates coordinates)
        {
            return tiles[coordinates.getX()][coordinates.getY()];
        }

        public ClientGameHandler(int clientNumber, Board board, Label label, ClientCommunicator clientCommunicator)
        {
            pieceColor = (clientNumber == 1 ? PieceColor.WHITE : PieceColor.BLACK);
            pieceColorChar = (pieceColor == PieceColor.WHITE ? 'w' : 'b');
            this.board = board;
            tiles = board.getTiles();
            this.label = label;
            this.clientCommunicator = clientCommunicator;
            for (int i = 0; i < tiles.length; i++)
                for (int j = 0; j < tiles[0].length; j++)
                {
                    int finalI = i;
                    int finalJ = j;
                    tiles[i][j].getStackPane().setOnMouseClicked(event -> clickHandle(tiles[finalI][finalJ]));
                }
        }

    public void update()
    {
        System.out.println("waiting");
        Command command = new Command(CommandType.UNKNOWN);
        while (!(command.getCommandType() == CommandType.TURN && command.getPieceColor() == pieceColor))
        {
            command = clientCommunicator.getCommand();
            if(command.getCommandType() == CommandType.PLACE)
            {
                TileCoordinates coordinates = command.getCoordinates();
                tileFromCoordinates(coordinates).placePiece(new Piece(command.getPieceColor()));
            }
            else if(command.getCommandType() == CommandType.REMOVE)
            {
                TileCoordinates coordinates = command.getCoordinates();
                tileFromCoordinates(coordinates).removePiece();
            }
            else if(command.getCommandType() == CommandType.QUEEN)
            {
                TileCoordinates coordinates = command.getCoordinates();
                tileFromCoordinates(coordinates).getPiece().makeQueen();
            }
            else if(command.getCommandType() == CommandType.WIN)
            {
                endGame(command.getPieceColor());
            }
        }
        label.setText(TextStrings.yourTurnMsg);
        clickPossible = true;
    }

    void clickHandle(Tile tile)
    {
        if(!clickPossible)
            return;
        if (tile.hasPiece())
        {
            if (tile.getPieceColor() != pieceColor)
                return;
            select(tile);
            getPossibleMoves(tile);
        }
        else if (possibleMoves != null && selected != null)
        {
            if (possibleMovesTiles.contains(tile))
            {
                TileCoordinates from = new TileCoordinates(selected);
                TileCoordinates to = new TileCoordinates(tile);
                clickPossible = false;
                label.setText(TextStrings.opponentTurnMsg);
                resetPossibleMoves();
                resetSelection();

                clientCommunicator.sendMove(from, to);
                update();

            }
        }
    }

    void endGame(PieceColor winner)
    {
        clickPossible = false;
        label.setText(winner == PieceColor.BLACK ? TextStrings.blackWinMsg : TextStrings.whiteWinMsg);
    }

    void resetSelection()
    {
        if (selected == null)
            return;

        selected.setSelected(false);
        selected = null;
    }

    void select(Tile tile)
    {
        if (selected != null)
        {
            selected.setSelected(false);
        }
        selected = tile;
        selected.setSelected(true);
    }

    void resetPossibleMoves()
    {
        if (possibleMoves == null)
            return;

        for (TileCoordinates possible : possibleMoves)
        {
            tiles[possible.getX()][possible.getY()].setPossible(false);
        }
        possibleMoves = null;
        possibleMovesTiles = null;
    }

    void getPossibleMoves(Tile tile)
    {
        resetPossibleMoves();
        possibleMoves = clientCommunicator.getPossibleMoves(new TileCoordinates(tile));
        possibleMovesTiles = new ArrayList<>();
        for (TileCoordinates possible : possibleMoves)
        {
            tiles[possible.getX()][possible.getY()].setPossible(true);
            possibleMovesTiles.add(tileFromCoordinates(possible));
        }
    }
}
