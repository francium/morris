package se2aa4.morris;
//
public class Game {

	private boolean started;
	private Player whosTurn;
	private boolean isMoveMade;
	private Node selected;
	private Frame frame;
	
	public Game() {
		started = false;
		randTurn();
		isMoveMade = false;
		selected = Node.NONODE;
	}
	
	private void randTurn() {
		whosTurn = Math.random() > 0.5 ? Player.RED : Player.BLUE;
	}
	
	private void nextTurn() {
		if (whosTurn == Player.RED) whosTurn = Player.BLUE;
		else whosTurn = Player.RED;
	}

	public boolean isStarted() { return started; }

	public Player getWhosTurn() { return whosTurn; }

	public Node getSelected() { return selected; }

	public void setSelected(Node node) { selected = node; }
	
	public boolean getIsMoveMade() { return isMoveMade; }
	
	public boolean getIsValid() { return frame.getIsValid(); }

	public void newGame() {
		started = true;
		randTurn();
		selected = Node.NONODE;
		frame = new Frame();
	}
	
	public void endTurn() {
		nextTurn();
		selected = Node.NONODE;
		frame.createRestorePoint();
	}
	
	public void move(Node there) {
		isMoveMade = true;
		frame.move(selected, there);
	}
	
	public void restore() {
		selected = Node.NONODE;
		frame.restore();
	}
	
}