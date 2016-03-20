package se2aa4.morris;

import se2aa4.morris.enums.Location;
import se2aa4.morris.enums.Piece;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Frame data and methods
 */
public class Frame implements Serializable {

    // board and piece data
    private Map<Location, Piece> frame, frameBak;

    // board fields
	private boolean valid, validBak;
	private Location invalidLocation, invalidLocationBak;

	/**
	 * Frame object constructor
	 */
	public Frame() {
        valid = true;
        frame = new HashMap<>(28);

        for (Location l: Location.values()) {
            frame.put(l, Piece.NONE);
        }

        frame.put(Location.iB0, Piece.B0);
        frame.put(Location.iB1, Piece.B1);
        frame.put(Location.iB2, Piece.B2);
        frame.put(Location.iB3, Piece.B3);
        frame.put(Location.iB4, Piece.B4);
        frame.put(Location.iB5, Piece.B5);

        frame.put(Location.iR0, Piece.R0);
        frame.put(Location.iR1, Piece.R1);
        frame.put(Location.iR2, Piece.R2);
        frame.put(Location.iR3, Piece.R3);
        frame.put(Location.iR4, Piece.R4);
        frame.put(Location.iR5, Piece.R5);
	}

    public AbstractMap.SimpleEntry<Location, Piece>[] getFrame() {
        AbstractMap.SimpleEntry<Location, Piece>[] pairs;
        pairs = new AbstractMap.SimpleEntry[28];
        int i = 0;
        for (Location loc: frame.keySet()) {
            if (loc != Location.NONE) {
                pairs[i++] = new AbstractMap.SimpleEntry<>(loc, frame.get(loc));
            }
        }
        return pairs;
    }

	/**
	 * Check if frame is valid
	 * @return is frame valid
     */
	public boolean getValid() {
		return valid;
	}

	/**
	 * Get invalid node
	 * @return invalid node
     */
	public Location getInvalidLocation() {
		return invalidLocation;
	}

	/**
	 * Move piece to another location on the frame
	 * @param here location to move
	 * @param there destination location
     */
	public void move(Location here, Location there) {
        if (frame.get(there) != Piece.NONE) {
            // overlapping piece
            valid = false;
            invalidLocation = there;
        } else {
            frame.replace(there, frame.get(here));
            frame.replace(here, Piece.NONE);
        }
	}

    public void remove(Location there) {
        frame.remove(there);
    }

	/**
	 * Create restore point of game state
	 */
	public void createRestorePoint() {
        validBak = valid;
        invalidLocationBak = invalidLocation;
        frameBak = new HashMap<Location, Piece>(frame);
        for (Location loc: frameBak.keySet()) {
        }
	}

	/**
	 * Restore game state
	 */
	public void restore() {
        valid = validBak;
        invalidLocation = invalidLocationBak;
        frame = frameBak;
	}

    public Piece getPieceByLocation(Location l) {
        return frame.get(l);
    }

}