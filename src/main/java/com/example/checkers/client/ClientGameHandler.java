package com.example.checkers.client;

import com.example.checkers.common.*;
import com.example.checkers.data.TextStrings;
import javafx.scene.control.Label;
import java.util.ArrayList;
/**
 * class of ClientGameHandler
 */
public class ClientGameHandler
{
    private final Tile[][] tiles;
    private final Label label;
    private Tile selected = null;
    private boolean clickPossible = false;
    private final ClientCommunicator clientCommunicator;
    private CommandQueue commandQueue = new CommandQueue();
    private ClientUpdater clientUpdater;
    private ReceiverThread receiverThread;

    private ArrayList<TileCoordinates> possibleMoves = new ArrayList<>();
    private ArrayList<Tile> possibleMovesTiles = new ArrayList<>();

    PieceColor pieceColor;
    char pieceColorChar;

        private Tile tileFromCoordinates(TileCoordinates coordinates)
        {
            return tiles[coordinates.getX()][coordinates.getY()];
        }

        public ClientGameHandler(int clientNumber, Board board, Label label, ClientCommunicator clientCommunicator)
        {
            pieceColor = (clientNumber == 1 ? PieceColor.WHITE : PieceColor.BLACK);
            pieceColorChar = (pieceColor == PieceColor.WHITE ? 'w' : 'b');
            tiles = board.getTiles();
            this.label = label;
            this.clientCommunicator = clientCommunicator;
            for (int i = 0; i < tiles.length; i++)
                for (int j = 0; j < tiles[0].length; j++)
                {
                    int finalI = i;
                    int finalJ = j;
                    tiles[i][j].getStackPane().setOnMouseClicked(event -> clickHandle(tiles[finalI][finalJ]));
                }
            this.clientUpdater = new ClientUpdater(commandQueue, this);
            clientUpdater.start();
            receiverThread = new ReceiverThread(clientCommunicator, commandQueue);
            receiverThread.start();
        }

    public void update(Command command)
    {
            if(command.getCommandType() == CommandType.PLACE)
            {
                TileCoordinates coordinates = command.getCoordinates();
                tileFromCoordinates(coordinates).placePiece(new Piece(command.getPieceColor()));
            }
            else if(command.getCommandType() == CommandType.REMOVE)
            {
                TileCoordinates coordinates = command.getCoordinates();
                tileFromCoordinates(coordinates).removePiece();
            }
            else if(command.getCommandType() == CommandType.POSSIBLE)
            {
                TileCoordinates coordinates = command.getCoordinates();
                tileFromCoordinates(coordinates).setPossible(true);
                possibleMoves.add(coordinates);
                possibleMovesTiles.add(tileFromCoordinates(coordinates));
            }
            else if(command.getCommandType() == CommandType.QUEEN)
            {
                TileCoordinates coordinates = command.getCoordinates();
                tileFromCoordinates(coordinates).getPiece().makeQueen();
            }
            else if(command.getCommandType() == CommandType.WIN)
            {
                endGame(command.getPieceColor());
            }
            else if(command.getCommandType() == CommandType.TURN)
            {
                if(command.getPieceColor() == pieceColor)
                {
                    label.setText(TextStrings.yourTurnMsg);
                    clickPossible = true;
                }
                else
                {
                    label.setText(TextStrings.opponentTurnMsg);
                    clickPossible = false;
                }
            }
    }

    void clickHandle(Tile tile)
    {
        if(!clickPossible)
            return;
        if (tile.hasPiece())
        {
            if (tile.getPieceColor() != pieceColor)
                return;
            select(tile);
            resetPossibleMoves();
            clientCommunicator.getPossibleMoves(new TileCoordinates(tile));
        }
        else if (possibleMoves != null && selected != null)
        {
            if (possibleMovesTiles.contains(tile))
            {
                TileCoordinates from = new TileCoordinates(selected);
                TileCoordinates to = new TileCoordinates(tile);
                clientCommunicator.sendMove(from, to);
                resetPossibleMoves();
                resetSelection();
            }
        }
    }

    void endGame(PieceColor winner)
    {
        clickPossible = false;
        label.setText(winner == PieceColor.BLACK ? TextStrings.blackWinMsg : TextStrings.whiteWinMsg);
    }

    void resetSelection()
    {
        if (selected == null)
            return;

        selected.setSelected(false);
        selected = null;
    }

    void select(Tile tile)
    {
        if (selected != null)
        {
            selected.setSelected(false);
        }
        selected = tile;
        selected.setSelected(true);
    }

    void resetPossibleMoves()
    {
        if (possibleMoves.size() == 0)
            return;

        for (TileCoordinates possible : possibleMoves)
        {
            tiles[possible.getX()][possible.getY()].setPossible(false);
        }
        possibleMoves = new ArrayList<>();
        possibleMovesTiles = new ArrayList<>();
    }

    /*void getPossibleMoves(Tile tile)
    {
        resetPossibleMoves();
        possibleMoves = clientCommunicator.getPossibleMoves(new TileCoordinates(tile));
        possibleMovesTiles = new ArrayList<>();
        for (TileCoordinates possible : possibleMoves)
        {
            tiles[possible.getX()][possible.getY()].setPossible(true);
            possibleMovesTiles.add(tileFromCoordinates(possible));
        }
    }*/
}
