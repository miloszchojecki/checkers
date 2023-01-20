package com.example.checkers.common;
/**
 * class of TileCoordinates
 */
public class TileCoordinates
{
    private final int x;
    private final int y;
    /**
     * TileCoordinates constructor from x, y
     */
    public TileCoordinates(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    /**
     * TileCoordinates constructor from tile
     */
    public TileCoordinates(Tile tile)
    {
        this.x = tile.getX();
        this.y = tile.getY();
    }
    /**
     * Method that gets X
     */
    public int getX()
    {
        return x;
    }
    /**
     * Method that gets Y
     */
    public int getY()
    {
        return y;
    }
    /**
     * Method that makes coordinates to string
     */
    public String toString()
    {
        return x + "" + y;
    }

    public boolean equals(TileCoordinates tileCoordinates)
    {
        return this.x == tileCoordinates.getX() && this.y == tileCoordinates.getY();
    }
}
