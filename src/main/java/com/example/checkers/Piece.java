package com.example.checkers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece
{
    PieceColor pieceColor;
    Circle circle;

    private final Color blackCircleColor = Color.BLACK;
    private final Color whiteCircleColor = Color.WHITE;

    private final int radius = 40;

    public Piece(PieceColor pieceColor)
    {
        this.pieceColor = pieceColor;
        if(pieceColor == PieceColor.BLACK)
            circle = new Circle(radius, blackCircleColor);
        else
            circle = new Circle(radius, whiteCircleColor);
    }

    public Circle getCircle()
    {
        return circle;
    }
}
