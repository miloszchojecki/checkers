package com.example.checkers.client.bot;

import com.example.checkers.client.ClientCommunicator;
import com.example.checkers.client.bot.strategy.RandomStrategy;
import com.example.checkers.client.bot.strategy.Strategy;
import com.example.checkers.common.*;

import java.util.ArrayList;

public class BotGameHandler
{
    PieceColor pieceColor;
    private final ClientCommunicator clientCommunicator;
    private final Tile[][] tiles;
    private ArrayList<MoveCoordinates> possibleMoves = new ArrayList<>();

    private final Strategy strategy = new RandomStrategy();

    public BotGameHandler(int clientNumber, ClientCommunicator clientCommunicator)
    {
        pieceColor = (clientNumber == 1 ? PieceColor.WHITE : PieceColor.BLACK);
        this.clientCommunicator = clientCommunicator;
        Board gameBoard = new Board();
        tiles = gameBoard.getTiles();
        update();
    }

    private Tile tileFromCoordinates(TileCoordinates coordinates)
    {
        return tiles[coordinates.getX()][coordinates.getY()];
    }

    public void update()
    {
        while (true)
        {
            Command command = clientCommunicator.getCommand();
            if (command.getCommandType() == CommandType.PLACE)
            {
                TileCoordinates coordinates = command.getCoordinates();
                tileFromCoordinates(coordinates).placePiece(new Piece(command.getPieceColor()));
            }
            else if (command.getCommandType() == CommandType.REMOVE)
            {
                TileCoordinates coordinates = command.getCoordinates();
                tileFromCoordinates(coordinates).removePiece();
            }
            else if (command.getCommandType() == CommandType.POSSIBLE)
            {
                TileCoordinates fromCoordinates = command.getCoordinates();
                TileCoordinates toCoordinates = command.getNextCoordinates();
                possibleMoves.add(new MoveCoordinates(fromCoordinates, toCoordinates));
            }
            else if (command.getCommandType() == CommandType.QUEEN)
            {
                TileCoordinates coordinates = command.getCoordinates();
                tileFromCoordinates(coordinates).getPiece().makeQueen();
            }
            else if (command.getCommandType() == CommandType.TURN && command.getPieceColor() == pieceColor)
            {
                possibleMoves = new ArrayList<>();
                for(int i = 0; i< tiles.length; i++)
                {
                    for (int j=0; j<tiles[i].length; j++)
                    {
                        if(tiles[i][j].getPieceColor() == pieceColor)
                        {
                            possibleMoves.addAll(clientCommunicator.getPossibleMoves(new TileCoordinates(i,j)));
                        }
                    }
                }
                System.out.print("Possible moves: ");
                for(MoveCoordinates move : possibleMoves)
                {
                    System.out.print(move.getFrom() + "->" + move.getTo() + " ");
                }
                MoveCoordinates move = strategy.makeMove(possibleMoves);
                System.out.println();
                System.out.println("Choosing " + move.getFrom() + "->" + move.getTo());
                clientCommunicator.sendMove(move.getFrom(), move.getTo());
            }
        }
    }

}
