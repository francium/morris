package se2aa4.morris;

public enum Piece {
	NONE("none"),
	PIECE_R0("pieceR0"), PIECE_R1("pieceR1"), PIECE_R2("pieceR2"), PIECE_R3("pieceR3"), PIECE_R4("pieceR4"), PIECE_R5("pieceR5"), 
	PIECE_B0("pieceB0"), PIECE_B1("pieceB1"), PIECE_B2("pieceB2"), PIECE_B3("pieceB3"), PIECE_B4("pieceB4"), PIECE_B5("pieceB5"); 
	
	private String id;
	
	private Piece(String id) { this.id = id; }
	
	public String getId() { return this.id; }
}
