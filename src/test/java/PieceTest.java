import com.example.checkers.common.Piece;
import com.example.checkers.common.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest
{
    Piece piece = new Piece(PieceColor.BLACK);

    @Test
    void testColor()
    {
        assertEquals(PieceColor.BLACK, piece.getPieceColor());
    }

    @Test
    void testQueen()
    {
        assertFalse(piece.isQueen());
        piece.makeQueen();
        assertTrue(piece.isQueen());
    }
}
