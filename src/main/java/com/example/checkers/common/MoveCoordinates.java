package com.example.checkers.common;

public class MoveCoordinates
{
    private TileCoordinates from, to;

    public MoveCoordinates(TileCoordinates from, TileCoordinates to)
    {
        this.from = from;
        this.to = to;
    }

    public TileCoordinates getFrom()
    {
        return from;
    }

    public TileCoordinates getTo()
    {
        return to;
    }
}
