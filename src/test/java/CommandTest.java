import com.example.checkers.common.Command;
import com.example.checkers.common.CommandType;
import com.example.checkers.common.TileCoordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest
{
    TileCoordinates from = new TileCoordinates(1, 2);
    TileCoordinates to = new TileCoordinates(3, 4);
    Command command = new Command(CommandType.MOVE, from, to);

    @Test
    void testCommandType()
    {
        assertEquals(CommandType.MOVE, command.getCommandType());
    }

    @Test
    void testCommandCoordinates()
    {
        assertEquals(from, command.getCoordinates());
        assertEquals(to, command.getNextCoordinates());
    }
}
