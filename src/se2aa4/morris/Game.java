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
    private Phase phase, phaseBak;
    private Player turn, turnBak;
    private Location sel, selBak;
    private boolean moved, movedBak;
    private boolean multipleMoves, multipleMovesBak;

    public Game() {
        // TODO
        state = State.UNSTARTED;
    }

    public void newGame() {
        // TODO
        frame = new Frame();
        state = State.IN_PROGRESS;
        phase = Phase.MOVE;
        sel = Location.NONE;
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
            createRestorePoint();
            return Detail.END_TURN;
        }
    }

    public void handleMove(Location l) {
        // TODO
        // move their own piece to board
        // move their own piece around board
        // remove opp piece if mill
        // something is already selected
        if (sel == Location.NONE &&
                !Piece.isPlayers(turn, frame.getPieceByLocation(l)) &&
                phase != Phase.REMOVE)
            // other players piece without mill
            return;
        else if (Piece.isPlayers(turn, frame.getPieceByLocation(l))){
            // reselect another piece
            sel = l;
            return;
        }
        switch (phase) {
            // user click on empty location
            case PLACE:
                frame.move(sel, l);
                sel = Location.NONE;
                if (moved) multipleMoves = true;
                moved = true;
                break;
            case MOVE:
                frame.move(sel, l);
                sel = Location.NONE;
                if (moved) multipleMoves = true;
                moved = true;
                break;
            case REMOVE:
                frame.remove(l);
                moved = true;
                break;
            default:
                //
        }
    }

    public void createRestorePoint() {
        stateBak = state;
        phaseBak = phase;
        turnBak = turn;
        selBak = sel;
        movedBak = moved;
        multipleMovesBak = multipleMoves;
        frame.createRestorePoint();
    }

    public void restore() {
        state = stateBak;
        phase = phaseBak;
        turn = turnBak;
        sel = selBak;
        moved = movedBak;
        multipleMoves = multipleMovesBak;
        frame.restore();
        createRestorePoint();
    }

    public boolean isMill() {
        // TODO
        return false;
    }

    public State getState() {
        return state;
    }

    public Phase getPhase() {
        return phase;
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


}