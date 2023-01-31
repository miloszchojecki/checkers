package com.example.checkers.server;

import com.example.checkers.common.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * class of ServerCommunicator
 */
public class ServerCommunicator
{
    /*
    out:
    pxyc - place piece of color c on x,y
    rxy - remove piece from x,y
    qxy - make piece on x,y queen
    tc - turn of player of color c
    wc - player c wins
    xabcd - move possible from a,b to c,d

    in:
    sxy - selected tile x,y
    mxyab - move piece from x,y to a,b
     */
    BufferedReader firstInput, secondInput;
    PrintWriter firstOutput, secondOutput;

    private String message;

    public ServerCommunicator(BufferedReader firstInput, PrintWriter firstOutput, BufferedReader secondInput, PrintWriter secondOutput)
    {
        this.firstInput = firstInput;
        this.firstOutput = firstOutput;
        this.secondInput = secondInput;
        this.secondOutput = secondOutput;
    }

    private BufferedReader getPlayerInput(PieceColor player)
    {
        return (player == PieceColor.WHITE ? firstInput : secondInput);
    }

    private PrintWriter getPlayerOutput(PieceColor player)
    {
        return (player == PieceColor.WHITE ? firstOutput : secondOutput);
    }

    private char getPieceColorChar(PieceColor pieceColor)
    {
        return (pieceColor == PieceColor.BLACK ? 'b' : 'w');
    }

    public void initialize(Tile[][] tiles)
    {
        for (int i = 0; i < tiles.length; i++)
        {
            for (int j = 0; j < tiles[i].length; j++)
            {
                if (tiles[i][j].hasPiece())
                {
                    PieceColor pieceColor = tiles[i][j].getPieceColor();
                    char pieceColorChar = (pieceColor == PieceColor.BLACK ? 'b' : 'w');
                    message = "p" + i + j + pieceColorChar;
                    System.out.println("server to 1, 2: " + message);
                    firstOutput.println(message);
                    secondOutput.println(message);
                }
            }
        }
    }

    public Command getCommand(PieceColor client)
    {
        BufferedReader in = getPlayerInput(client);
        try
        {
            String message = in.readLine();
            if (message.charAt(0) == 's')
            {
                TileCoordinates selectedTileCoordinates = getSelectedTile(message);
                return new Command(CommandType.SELECT, selectedTileCoordinates);
            }
            else if (message.charAt(0) == 'm')
            {
                TileCoordinates moveFromCoordinates = getFromCoordinates(message);
                TileCoordinates moveToCoordinates = getToCoordinates(message);
                return new Command(CommandType.MOVE, moveFromCoordinates, moveToCoordinates);
            }
            else
                return new Command(CommandType.UNKNOWN);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    public void setTurn(PieceColor turn)
    {
        char playerChar = getPieceColorChar(turn);
        message = "t" + playerChar;
        System.out.println("server to 1, 2:" + message);
        firstOutput.println(message);
        secondOutput.println(message);
    }

    public void sendPossibleMoves(PieceColor player, ArrayList<TileCoordinates> tileCoordinates)
    {
        PrintWriter out = getPlayerOutput(player);
        System.out.println("server to player " + player + " (possible moves): " + tileCoordinates.size());
        out.println(tileCoordinates.size());
        for(TileCoordinates tile : tileCoordinates)
        {
            message = "x" + tile.toString();
            System.out.println(message);
            out.println(message);
        }
    }

    public void sendMoveInfo(TileCoordinates from, TileCoordinates to, MoveInfo moveInfo)
    {
        //remove
        message = "r" + from.toString();
        System.out.println("server to 1, 2:" + message);
        firstOutput.println(message);
        secondOutput.println(message);

        //place
        message = "p" + to.toString() + getPieceColorChar(moveInfo.getPieceColor());
        System.out.println("server to 1, 2:" + message);
        firstOutput.println(message);
        secondOutput.println(message);

        //remove killed piece
        if(moveInfo.getKilledPiece() != null)
        {
            message = "r" + moveInfo.getKilledPiece().toString();
            System.out.println("server to 1, 2:" + message);
            firstOutput.println(message);
            secondOutput.println(message);
        }

        //make queen
        if(moveInfo.isQueen())
        {
            message = "q" + to.toString();
            System.out.println("server to 1, 2:" + message);
            firstOutput.println(message);
            secondOutput.println(message);
        }

        //winner or turn change
        if(moveInfo.getWinner() != null)
        {
            message = "w" + getPieceColorChar(moveInfo.getWinner());
            System.out.println("server to 1, 2:" + message);
            firstOutput.println(message);
            secondOutput.println(message);
        }
        else
        {
            setTurn(moveInfo.getNextTurn());
        }
    }

    public TileCoordinates getSelectedTile(String message)
    {
        if(message.charAt(0) != 's')
            return null;
        return new TileCoordinates(Integer.parseInt(String.valueOf(message.charAt(1))), Integer.parseInt(String.valueOf(message.charAt(2))));
    }

    public TileCoordinates getFromCoordinates(String message)
    {
        if(message.charAt(0) != 'm')
            return null;
        return new TileCoordinates(Integer.parseInt(String.valueOf(message.charAt(1))), Integer.parseInt(String.valueOf(message.charAt(2))));
    }

    public TileCoordinates getToCoordinates(String message)
    {
        if(message.charAt(0) != 'm')
            return null;
        return new TileCoordinates(Integer.parseInt(String.valueOf(message.charAt(3))), Integer.parseInt(String.valueOf(message.charAt(4))));
    }
}
