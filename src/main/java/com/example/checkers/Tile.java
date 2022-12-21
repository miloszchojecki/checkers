package com.example.checkers;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Tile
{
    private StackPane stackPane;
    private Rectangle rectangle;
    private Piece piece;
    private Circle circle;
    private boolean playable;

    private int x, y;

    private final Color playableColor = Color.rgb(118, 150, 86);
    private final Color notPlayableColor = Color.rgb(238, 238, 210);

    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;
        stackPane = new StackPane();
        rectangle = new Rectangle(100, 100);
        stackPane.getChildren().add(rectangle);
        setPlayable(false);
    }

    public void setPlayable(boolean playable)
    {
        this.playable = playable;
        if(playable)
        {
            rectangle.setFill(playableColor);
        }
        else
        {
            rectangle.setFill(notPlayableColor);
        }
    }
    public StackPane getStackPane()
    {
        return stackPane;
    }

    public boolean hasPiece()
    {
        return piece != null;
    }

    public boolean isPlayable()
    {
        return playable;
    }

    public void placePiece(Piece piece)
    {
        this.piece = piece;
        this.circle = piece.getCircle();
        stackPane.getChildren().add(circle);
    }

    public void removePiece()
    {
        stackPane.getChildren().remove(circle);
        circle = null;
        piece = null;
    }
}
