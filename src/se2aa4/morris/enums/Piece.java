package se2aa4.morris.enums;

/**
 *
 */
public enum Piece {
    NONE,
    B0, B1, B2, B3, B4, B5,
    R0, R1, R2, R3, R4, R5;

    public static boolean isPlayers(Player player, Piece piece) {
        if (player == Player.RED) {
            if (piece.toString().contains("R")) return true;
        } else {
            if (piece.toString().contains("B")) return true;
        }
        return false;
    }

    public static boolean isSamePlayer(Piece p1, Piece p2, Piece p3) {
        if (p1.toString().contains("R") && p2.toString().contains("R") &&
                p3.toString().contains("R")) return true;
        else if (p1.toString().contains("B") && p2.toString().contains("B") &&
                p3.toString().contains("B")) return true;
        else return false;
    }
}
