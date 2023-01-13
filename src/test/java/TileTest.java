import com.example.checkers.common.Piece;
import com.example.checkers.common.PieceColor;
import com.example.checkers.common.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest
{
    Tile tile = new Tile(1, 1);

    @Test
    void testXY()
    {
        assertEquals(1, tile.getX());
        assertEquals(1, tile.getY());
    }

    @Test
    void testPlayable()
    {
        assertFalse(tile.isPlayable());
        tile.setPlayable(true);
        assertTrue(tile.isPlayable());
    }

    @Test
    void testPiece()
    {
        assertFalse(tile.hasPiece());
        tile.placePiece(new Piece(PieceColor.WHITE));
        assertTrue(tile.hasPiece());
        assertEquals(PieceColor.WHITE, tile.getPieceColor());
        tile.removePiece();
        assertFalse(tile.hasPiece());
    }
}
