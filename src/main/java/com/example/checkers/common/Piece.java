package com.example.checkers.common;

import com.example.checkers.data.ColorSet;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
/**
 * class of Piece
 */
public class Piece
{
    /**
     * making object pieceColor
     */
    PieceColor pieceColor;
    /**
     * making object stackPane
     */
    StackPane stackPane;
    /**
     * making object outerCircle and innerCircle
     */
    Circle outerCircle, innerCircle;

    private boolean isQueen = false;

    private final Color blackCircleColor = ColorSet.blackPieceColor;
    private final Color whiteCircleColor = ColorSet.whitePieceColor;
    private final Color innerCircleColor = ColorSet.queenPieceColor;;

    private final int radius = 40;

    /**
     * Piece constructor
     */
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

    /**
     * Method that gets CircleStackPane
     */
    public StackPane getCircleStackPane()
    {
        return stackPane;
    }
    /**
     * Method that gets PieceColor
     */
    public PieceColor getPieceColor()
    {
        return pieceColor;
    }
    /**
     * Method that checks if Piece is Queen
     */
    public boolean isQueen()
    {
        return isQueen;
    }
    /**
     * Method that makes Queen
     */
    public void makeQueen()
    {
        innerCircle = new Circle(radius * 0.5, innerCircleColor);
        stackPane.getChildren().add(innerCircle);
        isQueen = true;
    }
}
