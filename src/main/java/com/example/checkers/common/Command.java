package com.example.checkers.common;

public class Command
{
    private CommandType commandType;
    private PieceColor pieceColor;
    private TileCoordinates coordinates, nextCoordinates;

    public Command(CommandType commandType)
    {
        this.commandType = commandType;
    }
    public Command(CommandType commandType, PieceColor pieceColor)
    {
        this.commandType = commandType;
        this.pieceColor = pieceColor;
    }

    public Command(CommandType commandType, TileCoordinates coordinates)
    {
        this.commandType = commandType;
        this.coordinates = coordinates;
    }
    public Command(CommandType commandType, TileCoordinates coordinates, PieceColor pieceColor)
    {
        this.commandType = commandType;
        this.coordinates = coordinates;
        this.pieceColor = pieceColor;
    }

    public Command(CommandType commandType, TileCoordinates coordinates, TileCoordinates nextCoordinates)
    {
        this.commandType = commandType;
        this.coordinates = coordinates;
        this.nextCoordinates = nextCoordinates;
    }

    public CommandType getCommandType()
    {
        return commandType;
    }

    public PieceColor getPieceColor()
    {
        return pieceColor;
    }

    public TileCoordinates getCoordinates()
    {
        return coordinates;
    }

    public TileCoordinates getNextCoordinates()
    {
        return nextCoordinates;
    }
}
