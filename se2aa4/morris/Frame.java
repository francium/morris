package se2aa4.morris;

import se2aa4.morris.enums.Location;
import se2aa4.morris.enums.Piece;
import se2aa4.morris.enums.Player;

import java.io.Serializable;
import java.util.*;

/**
 * Frame data and methods
 */
public class Frame implements Serializable {

    // board and piece data
    private Map<Location, Piece> frame, frameBak;

    // board fields
    private int numBluePieces, numRedPieces;
	private boolean valid, validBak;
	private Location invalidLocation, invalidLocationBak;
    private boolean redMill, blueMill, redMillBak, blueMillBak;
    private ArrayList<Location[]> blueMills, redMills, blueMillsBak, redMillsBak,
                                  blueMillsNew, redMillsNew;

    private Location[][] triplets = {
        {Location.nONW, Location.nON, Location.nONE},
        {Location.nONE, Location.nOE, Location.nOSE},
        {Location.nOSE, Location.nOS, Location.nOSW},
        {Location.nOSW, Location.nOW, Location.nONW},
        {Location.nINW, Location.nIN, Location.nINE},
        {Location.nINE, Location.nIE, Location.nISE},
        {Location.nISE, Location.nIS, Location.nISW},
        {Location.nISW, Location.nIW, Location.nINW},
    };


	/**
	 * Frame object constructor
	 */
	public Frame() {
        valid = true;
        frame = new HashMap<>(28);

        numBluePieces = 6;
        numRedPieces = 6;

        for (Location l: Location.values()) {
            frame.put(l, Piece.NONE);
        }

        blueMills = new ArrayList<>();
        redMills = new ArrayList<>();
        blueMillsNew = new ArrayList<>();
        redMillsNew = new ArrayList<>();

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

    /**
     * Get board data
     * @return board data
     */
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

    public boolean isRedMill() {
        return redMill;
    }

    public boolean isBlueMill() {
        return blueMill;
    }


	/**
	 * Check if frame is valid
	 * @return is frame valid
     */
	public boolean getValid() {
		return valid;
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

    public int getNumPieces(Player p) {
        if (p == Player.BLUE)
            return numBluePieces;
        else
            return numRedPieces;
    }

    /**
     * remove a piece from a location
     * @param there which location
     */
    public void remove(Location there) {
        if (Piece.isPlayers(Player.BLUE, getPieceByLocation(there)))
            numRedPieces--;
        else
            numBluePieces--;
        frame.put(there, Piece.NONE);
    }

	/**
	 * Create restore point of game state
	 */
	public void createRestorePoint() {
        validBak = valid;
        invalidLocationBak = invalidLocation;
        redMillBak = redMill;
        blueMillBak = blueMill;
        redMillsBak = redMills;
        blueMillsBak = blueMills;
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
        redMill = redMillBak;
        blueMill = blueMillBak;
        redMills = redMillsBak;
        blueMills = blueMillsBak;
	}

    /**
     * get piece based on board location
     * @param l location on board
     * @return piece at the location
     */
    public Piece getPieceByLocation(Location l) {
        return frame.get(l);
    }

    public void makeMillsOld(Player p) {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tmaking old");
        if (p == Player.BLUE) {
            blueMills = blueMillsNew;
        } else {
            redMills = redMillsNew;
        }
    }

    /**
     * Check if mill exists for either player
     */
    public void checkForMill(Player p) {
        ArrayList<Location[]> mills;
        if (p == Player.BLUE)
            mills = blueMillsNew;
        else
            mills = redMillsNew;

        mills.clear();

        for (int i=0; i<triplets.length; i++) {
            Location[] trip = triplets[i];
            if (Piece.isPlayers(p, frame.get(trip[1]))) {
                if (Piece.isSamePlayer(frame.get(trip[0]),
                                       frame.get(trip[1]),
                                       frame.get(trip[2]))) {
                    mills.add(trip);
                }
            }
        }

        System.out.print("detected mills for " + p + "\t\n");
        for (Location[] mill: redMills) {
            System.out.println("\t" + printMill(mill));
        }
        filterNewMills(p, mills);
    }

    /**
     * Check new mill for specific player
     * @param p
     * @param millsNew
     */
    private void filterNewMills(Player p, ArrayList<Location[]> millsNew) {
        ArrayList<Location[]> mills = p == Player.RED ? redMills : blueMills;

        System.out.println("\t\t\t\t\t\tnewMill size = " + millsNew.size());
        System.out.println("\t\t\t\t\t\toldMill size = " + mills.size());
        for (Location[] tripi: millsNew) {
            boolean newMill = false;
            if (mills.size() > 0) {
                for (Location[] tripj : mills) {
                    if (!isSameTriplet(tripi, tripj)) {
                        System.out.println("\t\t\t\t\t\tnew mill");
                        newMill = true;
                    }
                }
            } else {
                newMill = true;
            }

            if (newMill) {
                if (p == Player.RED) redMill = true;
                else blueMill = true;
                return;
            } else {
                blueMill = false;
                redMill = false;
            }
        }
    }

    public boolean isMillPiece(Location l) {
        for (Location[] trip: triplets) {
            for (Location i: trip) {
                if (i == l)
                    if (Piece.isSamePlayer(frame.get(trip[0]),
                                           frame.get(trip[1]),
                                           frame.get(trip[2]))) {
                        return true;
                    }

            }
        }
        return false;
    }

    private static String printMill(Location[] a, Location[] b) {
        String out = "";
        for (Location i: a) out += i + ", ";
        out += "\n";
        for (Location i: b) out += i + ", ";
        out += "\n";
        return out;
    }

    private static String printMill(Location[] a) {
        String out = "";
        for (Location i: a) out += i + ", ";
        out += "\n";
        return out;
    }

    /**
     * Do both arrays contain same entries
     * @param t1
     * @param t2
     * @return
     */
    private static boolean isSameTriplet(Location[] t1, Location[] t2) {
        for (int i=0; i<t1.length; i++) {
            if (!(t1[i] == (t2[i]))) return false;
        }
        return true;
    }

    /**
     * get if inventory is empty for a given player
     * @param player which player
     * @return is inventory empty
     */
    public boolean isInventoryEmpty(Player player) {
        for (Location l: Location.getInventory(player))
            if (frame.get(l) != Piece.NONE) return false;

        return true;
    }

    /**
     * is a move a fly move
     * @param l1 first location
     * @param l2 second location
     * @return is move between l1 and l2 a fly move
     */
    public static boolean isMoveFly(Location l1, Location l2) {
        // valid non-fly moves
        if (either(l1, l2, Location.nONW, Location.nON)) return false;
        else if (either(l1, l2, Location.nON, Location.nIN)) return false;
        else if (either(l1, l2, Location.nON, Location.nONE)) return false;
        else if (either(l1, l2, Location.nONE, Location.nOE)) return false;
        else if (either(l1, l2, Location.nOE, Location.nIE)) return false;
        else if (either(l1, l2, Location.nOE, Location.nOSE)) return false;
        else if (either(l1, l2, Location.nOSE, Location.nOS)) return false;
        else if (either(l1, l2, Location.nOS, Location.nIS)) return false;
        else if (either(l1, l2, Location.nOS, Location.nOSW)) return false;
        else if (either(l1, l2, Location.nOSW, Location.nOW)) return false;
        else if (either(l1, l2, Location.nOW, Location.nIW)) return false;
        else if (either(l1, l2, Location.nOW, Location.nONW)) return false;
        else if (either(l1, l2, Location.nINW, Location.nIN)) return false;
        else if (either(l1, l2, Location.nIN, Location.nINE)) return false;
        else if (either(l1, l2, Location.nINE, Location.nIE)) return false;
        else if (either(l1, l2, Location.nIE, Location.nISE)) return false;
        else if (either(l1, l2, Location.nISE, Location.nIS)) return false;
        else if (either(l1, l2, Location.nIS, Location.nISW)) return false;
        else if (either(l1, l2, Location.nISW, Location.nIW)) return false;
        else if (either(l1, l2, Location.nIW, Location.nINW)) return false;
        else return true;
    }

    /**
     * are either of the two parameters equal to other two parameters
     * @param l1 first parameter
     * @param l2 second parameter
     * @param c1 first comparison parameter
     * @param c2 second comparison parameter
     * @return are first two equal to second two in any combination
     */
    public static boolean either(Location l1, Location l2, Location c1, Location c2) {
        if (l1 == c1 || l1 == c2)
            if (l2 == c1 || l2 == c2)
                if (l1 != l2)
                    return true;
        return false;
    }

    public boolean movePossible(Player p){
        for (Map.Entry<Location, Piece> entry : frame.entrySet()){
            boolean playersColor = Piece.isPlayers(p, frame.get(entry.getKey()));

            if (!Location.isInventory(entry.getKey()) && playersColor && !Location.isInventory(entry.getKey())) {
                //find adjacent location of this piece
                ArrayList<Location> adjacentLocations = getAdjacent(entry.getKey());
                for (Location adj: adjacentLocations) {
                    if (frame.get(adj) == Piece.NONE)
                        return true;
                }
            } else if (Location.isInventory(entry.getKey()))
                return true;
        }
        return false;
    }


    private ArrayList<Location> getAdjacent(Location loc){
        ArrayList<Location> adjLoc = new ArrayList();
        switch(loc){
            case nONW : adjLoc.add(Location.nON);
                adjLoc.add(Location.nOW);
                break;
            case nON : adjLoc.add(Location.nONE);
                adjLoc.add(Location.nONW);
                adjLoc.add(Location.nIN);
                break;
            case nONE : adjLoc.add(Location.nOE);
                adjLoc.add(Location.nON);
                break;
            case nOE :  adjLoc.add(Location.nONE);
                adjLoc.add(Location.nOSE);
                adjLoc.add(Location.nIE);
                break;
            case nOSE : adjLoc.add(Location.nOE);
                adjLoc.add(Location.nOS);
                break;
            case nOS : adjLoc.add(Location.nOSE);
                adjLoc.add(Location.nOSW);
                adjLoc.add(Location.nIS);
                break;
            case nOSW : adjLoc.add(Location.nOS);
                adjLoc.add(Location.nOW);
                break;
            case nOW : adjLoc.add(Location.nONW);
                adjLoc.add(Location.nOSW);
                adjLoc.add(Location.nIW);
                break;
            case nINW : adjLoc.add(Location.nIN);
                adjLoc.add(Location.nIW);
                break;
            case nIN : adjLoc.add(Location.nINW);
                adjLoc.add(Location.nINE);
                adjLoc.add(Location.nON);
                break;
            case nINE : adjLoc.add(Location.nIN);
                adjLoc.add(Location.nIE);
                break;
            case nIE : adjLoc.add(Location.nINE);
                adjLoc.add(Location.nISE);
                adjLoc.add(Location.nOE);
                break;
            case nISE : adjLoc.add(Location.nIS);
                adjLoc.add(Location.nIE);
                break;
            case nIS : adjLoc.add(Location.nISE);
                adjLoc.add(Location.nISW);
                adjLoc.add(Location.nOS);
                break;
            case nISW : adjLoc.add(Location.nIS);
                adjLoc.add(Location.nIW);
                break;
            case nIW : adjLoc.add(Location.nINW);
                adjLoc.add(Location.nISW);
                adjLoc.add(Location.nOW);
                break;
            default : break;
        }
        return adjLoc;
    }

}
