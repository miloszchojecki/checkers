import com.example.checkers.common.Command;
import com.example.checkers.common.CommandQueue;
import com.example.checkers.common.CommandType;
import com.example.checkers.common.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandQueueTest
{
    private CommandQueue queue = new CommandQueue();
    private Command command = new Command(CommandType.TURN, PieceColor.BLACK);

    @Test
    void testCommand()
    {
        assertFalse(queue.isCommand());
        queue.addCommand(command);
        assertTrue(queue.isCommand());
        assertEquals(command, queue.getCommand());
        assertFalse(queue.isCommand());
        assertNull(queue.getCommand());
    }
}
