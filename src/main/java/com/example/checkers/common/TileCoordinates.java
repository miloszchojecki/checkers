package com.example.checkers.common;

public class TileCoordinates
{
    private final int x;
    private final int y;

    public TileCoordinates(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public TileCoordinates(Tile tile)
    {
        this.x = tile.getX();
        this.y = tile.getY();
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public String toString()
    {
        return x + "" + y;
    }
}
