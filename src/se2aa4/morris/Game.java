package se2aa4.morris;

import se2aa4.morris.enums.*;

import java.io.Serializable;
import java.util.AbstractMap;

/**
 * Game logic and data
 */
public class Game implements Serializable {

    private Frame frame;

    private State state, stateBak;
    private Player turn, turnBak;
    private Location sel, selBak;
    private boolean moved, movedBak;
    private boolean multipleMoves, multipleMovesBak;
    private boolean blueMillExists, redMillExists;
    private boolean redInventory, redInventoryBak,
                    blueInventory, blueInventoryBak;

    public Game() {
        state = State.UNSTARTED;
    }

    public void newGame() {
        frame = new Frame();
        state = State.IN_PROGRESS;
        sel = Location.NONE;
        blueMillExists = false;
        redMillExists = false;
        redInventory = true;
        blueInventory = true;
        moved = false;
        randTurn();
        createRestorePoint();
    }

    private void randTurn() {
        turn = Math.random() > 0.5 ? Player.RED : Player.BLUE;
    }

    /**
     * set to next player's turn
     */
    private void nextTurn() {
        if (turn == Player.RED) turn = Player.BLUE;
        else turn = Player.RED;
    }

    public Detail endTurn() {
        if (multipleMoves) {
            return Detail.MULTIPLE_MOVES;
        } else if (!moved) {
            return Detail.NO_MOVE;
        } else {
            nextTurn();
            moved = false;
            sel = Location.NONE;
            createRestorePoint();
            return Detail.END_TURN;
        }
    }

    public void handleMove(Location l) {
        if (sel != Location.NONE && l.toString().contains("n")) {
            if (Piece.isPlayers(turn, frame.getPieceByLocation(l))) {
                // reselect piece
                sel = l;
            }
            // piece is selected
            if (!l.toString().contains("n")) {
                // didn't click no board location
                if (!Piece.isPlayers(turn, frame.getPieceByLocation(l))) {
                    // clicked other players piece
                    if (turn == Player.BLUE) {
                        if (blueMillExists) {
                            // allow removal of players piece
                        } else {
                            return;
                        }
                    } else {
                        if (redMillExists) {
                            // allow removal of players piece
                        } else {
                            return;
                        }
                    }
                }
            } else {
                // clicked board location
                if (isInventoryEmpty(turn)) {
                    // allow moving of placed pieces
                    if (sel.toString().contains("n")) {
                        // selected piece is on board
                        if (l.toString().contains("n")) {
                            if (!frame.isMoveFly(sel, l))
                                move(l);
                        }
                    }
                } else {
                    // move inventory pieces
                    if (sel.toString().contains("i")) {
                        move(l);
                    }
                }
            }
        } else {
            // select piece
            if (Piece.isPlayers(turn, frame.getPieceByLocation(l))) {
                // player selected own piece
                sel = l;
            } else {
                // player selected opponent's piece
                if (turn == Player.BLUE && blueMillExists) {
                    sel = l;
                } else if (turn == Player.RED && redMillExists) {
                    sel = l;
                }
            }
        }
    }

    public void move(Location l) {
        frame.move(sel, l);
        sel = Location.NONE;
        if (moved) multipleMoves = true;
        moved = true;
        redInventory = frame.isInventoryEmpty(Player.RED);
        blueInventory = frame.isInventoryEmpty(Player.BLUE);
    }

    public void createRestorePoint() {
        stateBak = state;
        turnBak = turn;
        selBak = sel;
        movedBak = moved;
        multipleMovesBak = multipleMoves;
        frame.createRestorePoint();
        redInventoryBak = redInventory;
        blueInventoryBak = blueInventory;
    }

    public void restore() {
        state = stateBak;
        turn = turnBak;
        sel = selBak;
        moved = movedBak;
        multipleMoves = multipleMovesBak;
        redInventory = redInventoryBak;
        blueInventory = blueInventoryBak;
        frame.restore();
        createRestorePoint();
    }

    public boolean isMill() {
        System.out.println("mill " + frame.isMill());
        // TODO
        /*
        if (frame.isMill()) {
            if (millExists) {
                return false;
            } else {
                millExists = true;
                return true;
            }
        } else {
            millExists = false;
            return false;
        }
        */
        return false;
    }

    public State getState() {
        return state;
    }

    public Player getTurn() {
        return turn;
    }

    public Location getSel() {
        return sel;
    }

    public AbstractMap.SimpleEntry<Location, Piece>[] getBoard() {
        return frame.getFrame();
    }

    public Location getInvalidLocation() {
        return frame.getInvalidLocation();
    }

    public boolean isInventoryEmpty(Player player) {
        return frame.isInventoryEmpty(player);
    }

}