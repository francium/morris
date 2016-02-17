package se2aa4.morris;

public class Game {

	private Player whosTurn;
	private boolean isMoveMade;
	private Piece selPiece;
	private Frame frame;
	
	public Game() {
		isMoveMade = false;
		Piece selPiece = Piece.NONE;
	}
	
	public void newGame() {
		randTurn();
		Piece selPiece = Piece.NONE;
		frame = new Frame();
	}
	
	private void randTurn() {
		whosTurn = Math.random() > 0.5 ? Player.RED : Player.BLUE;
	}
}