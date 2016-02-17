package se2aa4.morris;

public class Frame {
	
	private Piece[] inventoryR, inventoryB;
	private Piece[] nodes;

	public Frame() {
		inventoryB = new Piece[] {Piece.PIECE_R0, Piece.PIECE_R1, Piece.PIECE_R2, Piece.PIECE_R3, Piece.PIECE_R4, Piece.PIECE_R5};
		inventoryB = new Piece[] {Piece.PIECE_B0, Piece.PIECE_B1, Piece.PIECE_B2, Piece.PIECE_B3, Piece.PIECE_B4, Piece.PIECE_B5};
		nodes = new Piece[16];
	}
}
