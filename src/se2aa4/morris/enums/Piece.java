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
}
