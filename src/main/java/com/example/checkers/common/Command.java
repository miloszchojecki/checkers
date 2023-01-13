package com.example.checkers.common;

/**
 * class with commands
 */
public class Command
{
    private CommandType commandType;
    private PieceColor pieceColor;
    private TileCoordinates coordinates, nextCoordinates;

    /**
     * Command constructor from command type
     */
    public Command(CommandType commandType)
    {
        this.commandType = commandType;
    }
    /**
     * Command constructor from command type and piece color
     */
    public Command(CommandType commandType, PieceColor pieceColor)
    {
        this.commandType = commandType;
        this.pieceColor = pieceColor;
    }
    /**
     * Command constructor from command type and coordinates
     */
    public Command(CommandType commandType, TileCoordinates coordinates)
    {
        this.commandType = commandType;
        this.coordinates = coordinates;
    }
    /**
     * Command constructor from command type, coordinates and piece color
     */
    public Command(CommandType commandType, TileCoordinates coordinates, PieceColor pieceColor)
    {
        this.commandType = commandType;
        this.coordinates = coordinates;
        this.pieceColor = pieceColor;
    }
    /**
     * Command constructor from command type, coordinates and next coordinates
     */
    public Command(CommandType commandType, TileCoordinates coordinates, TileCoordinates nextCoordinates)
    {
        this.commandType = commandType;
        this.coordinates = coordinates;
        this.nextCoordinates = nextCoordinates;
    }
    /**
     * Method that gets CommandType
     */
    public CommandType getCommandType()
    {
        return commandType;
    }
    /**
     * Method that gets PieceColor
     */
    public PieceColor getPieceColor()
    {
        return pieceColor;
    }
    /**
     * Method that gets Coordinates
     */
    public TileCoordinates getCoordinates()
    {
        return coordinates;
    }
    /**
     * Method that gets nextCoordinates
     */
    public TileCoordinates getNextCoordinates()
    {
        return nextCoordinates;
    }
}
