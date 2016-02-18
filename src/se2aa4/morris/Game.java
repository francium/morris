package se2aa4.morris;

public class Game {

	private boolean started = false;
	private Player whosTurn;
	private boolean isMoveMade;
	private Node selPiece;
	private Frame frame;
	
	public Game() {
		randTurn();
		isMoveMade = false;
		selPiece = Node.NONODE;
	}
	
	public void newGame() {
		started = true;
		randTurn();
		selPiece = Node.NONODE;
		frame = new Frame();
	}
	
	public boolean isStarted() { return started; }
	
	public Player getWhosTurn() { return whosTurn; }

	public Node getSelected() { return selPiece; }

	public void setSelected(Node node) {
		selPiece = node;
	}

	private void randTurn() {
		whosTurn = Math.random() > 0.5 ? Player.RED : Player.BLUE;
	}
	
}