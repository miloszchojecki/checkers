package com.example.checkers.common;

import com.example.checkers.data.ColorSet;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static com.example.checkers.data.ColorSet.notPlayableColor;
import static com.example.checkers.data.ColorSet.playableColor;
/**
 * class of Tile
 */
public class Tile
{
    private final StackPane stackPane;
    private final Rectangle rectangle;
    private Piece piece;
    private StackPane circleStackPane;
    private boolean playable;

    private final int x;
    private final int y;

    private Color defaultColor;

    private final Color selectedColor = ColorSet.selectedColor;

    private final Color possibleColor = ColorSet.possibleColor;
    /**
     * Tile constructor
     */
    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;
        stackPane = new StackPane();
        rectangle = new Rectangle(100, 100);
        stackPane.getChildren().add(rectangle);
        setPlayable(false);
    }
    /**
     * Method that sets color
     */
    void setColor(Color color)
    {
        rectangle.setFill(color);
    }
    /**
     * Method that sets playable
     */
    public void setPlayable(boolean playable)
    {
        this.playable = playable;
        defaultColor = playable ? playableColor : notPlayableColor;
        setColor(defaultColor);
    }
    /**
     * Method that sets selected
     */
    public void setSelected(boolean selected)
    {
        setColor(selected ? selectedColor : defaultColor);
    }
    /**
     * Method that sets possible
     */
    public void setPossible(boolean possible)
    {
        setColor(possible ? possibleColor : defaultColor);
    }
    /**
     * Method that sets stackPane
     */
    public StackPane getStackPane()
    {
        return stackPane;
    }
    /**
     * Method that checks if tile has piece
     */
    public boolean hasPiece()
    {
        return piece != null;
    }
    /**
     * Method that checks if it's playable
     */
    public boolean isPlayable()
    {
        return playable;
    }
    /**
     * Method that places piece
     */
    public void placePiece(Piece piece)
    {
        this.piece = piece;
        this.circleStackPane = piece.getCircleStackPane();
        stackPane.getChildren().add(circleStackPane);
    }
    /**
     * Method that removes piece
     */
    public void removePiece()
    {
        stackPane.getChildren().remove(circleStackPane);
        circleStackPane = null;
        piece = null;
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
     * Method that gets Piece
     */
    public Piece getPiece()
    {
        return piece;
    }
    /**
     * Method that gets piece color
     */
    public PieceColor getPieceColor()
    {
        if(piece == null)
            return null;
        return piece.getPieceColor();
    }
}
