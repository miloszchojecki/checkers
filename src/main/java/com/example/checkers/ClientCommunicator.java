package com.example.checkers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ClientCommunicator
{
    BufferedReader input;
    PrintWriter output;

    public ClientCommunicator(BufferedReader input, PrintWriter output)
    {
        this.input = input;
        this.output = output;
    }

    public TileCoordinates getCoordinates(String message)
    {
        return new TileCoordinates(Integer.parseInt(String.valueOf(message.charAt(1))), Integer.parseInt(String.valueOf(message.charAt(2))));
    }

    public TileCoordinates getNextCoordinates(String message)
    {
        return new TileCoordinates(Integer.parseInt(String.valueOf(message.charAt(3))), Integer.parseInt(String.valueOf(message.charAt(4))));
    }

    public PieceColor getPlacePieceColor(String message)
    {
        if(message.charAt(0) != 'p')
            return null;
        return (message.charAt(3) == 'w' ? PieceColor.WHITE : PieceColor.BLACK);
    }

    public PieceColor getPieceColor(String message)
    {
        return (message.charAt(1) == 'w' ? PieceColor.WHITE : PieceColor.BLACK);
    }

    public Command getCommand()
    {
        try
        {
            String message = input.readLine();
            if (message.charAt(0) == 'p')
            {
               TileCoordinates coordinates = getCoordinates(message);
               PieceColor placePieceColor = getPlacePieceColor(message);
               return new Command(CommandType.PLACE, coordinates, placePieceColor);
            }
            else if (message.charAt(0) == 'r')
            {
                TileCoordinates coordinates = getCoordinates(message);
                return new Command(CommandType.REMOVE, coordinates);
            }
            else if(message.charAt(0) == 'q')
            {
                TileCoordinates coordinates = getCoordinates(message);
                return new Command(CommandType.QUEEN, coordinates);
            }
            else if(message.charAt(0) == 'w')
            {
                PieceColor pieceColor = getPieceColor(message);
                return new Command(CommandType.WIN, pieceColor);
            }
            else if(message.charAt(0) == 't')
            {
                PieceColor pieceColor = getPieceColor(message);
                return new Command(CommandType.TURN, pieceColor);
            }
            else
                return new Command(CommandType.UNKNOWN);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<TileCoordinates> getPossibleMoves(TileCoordinates tileCoordinates)
    {
        String message = "s" + tileCoordinates.toString();
        output.println(message);
        ArrayList<TileCoordinates> possibleMoves = new ArrayList<>();
        try
        {
            int length = Integer.parseInt(input.readLine());
            for(int i=0; i<length; i++)
            {
                TileCoordinates coordinates = getCoordinates(input.readLine());
                possibleMoves.add(coordinates);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return possibleMoves;
    }

    public void sendMove(TileCoordinates from, TileCoordinates to)
    {
        String message = "m" + from.toString() + to.toString();
        output.println(message);
    }
}