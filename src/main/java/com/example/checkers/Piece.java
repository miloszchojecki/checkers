package com.example.checkers;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece
{
    PieceColor pieceColor;

    StackPane stackPane;
    Circle outerCircle, innerCircle;

    private boolean isQueen = false;

    private final Color blackCircleColor = ColorSet.blackPieceColor;
    private final Color whiteCircleColor = ColorSet.whitePieceColor;
    private final Color innerCircleColor = ColorSet.queenPieceColor;;

    private final int radius = 40;

    public Piece(PieceColor pieceColor)
    {
        this.pieceColor = pieceColor;
        if(pieceColor == PieceColor.BLACK)
        {
            outerCircle = new Circle(radius, blackCircleColor);
        }
        else
        {
            outerCircle = new Circle(radius, whiteCircleColor);
        }
        stackPane = new StackPane(outerCircle);
    }

    public StackPane getCircleStackPane()
    {
        return stackPane;
    }

    public PieceColor getPieceColor()
    {
        return pieceColor;
    }

    public boolean isQueen()
    {
        return isQueen;
    }

    public void makeQueen()
    {
        innerCircle = new Circle(radius * 0.5, innerCircleColor);
        stackPane.getChildren().add(innerCircle);
        isQueen = true;
    }
}
