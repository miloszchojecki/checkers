package com.example.checkers;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Game {
    private DummyGameLogic gameLogic;

    private Board board;

    private Label textbox;

    private Tile[][] tiles;

    private Tile selected = null;

    private ArrayList<Tile> possibleMoves;

    private PieceColor turn;

    private boolean movePossible = true;

    public Game(Label textbox) {
        this.textbox = textbox;
        board = new Board();
        tiles = board.getTiles();
    }

    public void startGame() {
        gameLogic = new DummyGameLogic(board);
        gameLogic.initialize();
        turn = PieceColor.WHITE;
        textbox.setText(Texts.whiteTurnMsg);

        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[0].length; j++) {
                int finalI = i;
                int finalJ = j;
                tiles[i][j].getStackPane().setOnMouseClicked(event -> clickHandle(tiles[finalI][finalJ]));
            }
    }

    void clickHandle(Tile tile) {
        System.out.println(tile.getX() + ", " + tile.getY());
        if (!movePossible)
            return;
        if (tile.hasPiece()) {
            if (tile.getPieceColor() != turn)
                return;
            select(tile);
            getPossibleMoves(tile);
        } else if (possibleMoves != null && selected != null) {
            if (possibleMoves.contains(tile)) {
                gameLogic.makeMove(selected, tile);
                if (gameLogic.checkWinner()) {
                    endGame(gameLogic.getWinner());
                    return;
                }
                changeTurn();
                resetPossibleMoves();
                resetSelection();
            }
        }
    }

    void changeTurn() {
        if (turn == PieceColor.WHITE) {
            turn = PieceColor.BLACK;
            textbox.setText(Texts.blackTurnMsg);
        } else {
            turn = PieceColor.WHITE;
            textbox.setText(Texts.whiteTurnMsg);
        }
    }

    void resetSelection() {
        if (selected == null)
            return;

        selected.setSelected(false);
        selected = null;
    }

    void select(Tile tile) {
        if (selected != null) {
            selected.setSelected(false);
        }
        selected = tile;
        selected.setSelected(true);
    }

    void resetPossibleMoves() {
        if (possibleMoves == null)
            return;

        for (Tile possible : possibleMoves) {
            possible.setPossible(false);
        }
        possibleMoves = null;
    }

    void getPossibleMoves(Tile tile) {
        resetPossibleMoves();
        possibleMoves = gameLogic.getPossibleMoves(tile);
        for (Tile possible : possibleMoves) {
            possible.setPossible(true);
        }
    }

    void endGame(PieceColor winner) {
        resetPossibleMoves();
        resetSelection();
        movePossible = false;
        textbox.setText(winner == PieceColor.WHITE ? Texts.whiteWinMsg : Texts.blackWinMsg);
    }

    public Board getBoard() {
        return board;
    }
}
