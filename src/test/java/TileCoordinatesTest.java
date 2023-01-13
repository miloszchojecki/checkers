import com.example.checkers.common.TileCoordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TileCoordinatesTest
{
    TileCoordinates tileCoordinates = new TileCoordinates(2, 4);

    @Test
    void testToString()
    {
        assertEquals("24", tileCoordinates.toString());
    }

    @Test
    void testGetX()
    {
        assertEquals(2, tileCoordinates.getX());
    }

    @Test
    void testGetY()
    {
        assertEquals(4, tileCoordinates.getY());
    }
}
