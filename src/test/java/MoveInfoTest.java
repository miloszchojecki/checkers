import com.example.checkers.common.MoveInfo;
import com.example.checkers.common.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoveInfoTest
{
    MoveInfo moveInfo = new MoveInfo(PieceColor.BLACK, PieceColor.WHITE, null, false, null);

    @Test
    void testNextTurn()
    {
        assertEquals(PieceColor.WHITE, moveInfo.getNextTurn());
    }

    @Test
    void testKilled()
    {
        assertNull(moveInfo.getKilledPiece());
    }

    @Test
    void testQueen()
    {
        assertFalse(moveInfo.isQueen());
    }
}
