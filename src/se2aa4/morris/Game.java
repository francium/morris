package se2aa4.morris;

/**
 * Game class (model)
 * Stores game state
 * @author Varun Hooda 001412942
 * @author Aushim Lakhana 001201528
 * @author Matthew Shortt 001417616
 * @version 1.0
 */
public class Game {

	private boolean started;
	private Player whosTurn;
	private boolean isMoveMade;
	private boolean areMultipleMovesMade;
	private Node selected;
	private Frame frame;
	private Node lastMove;

	/**
	 * Game object constructor
	 */
	public Game() {
		started = false;
		randTurn();
		isMoveMade = false;
		areMultipleMovesMade = false;
		selected = Node.NONODE;
	}

	// random player's turn
	private void randTurn() {
		whosTurn = Math.random() > 0.5 ? Player.RED : Player.BLUE;
	}

	// next player's turn
	private void nextTurn() {
		if (whosTurn == Player.RED) whosTurn = Player.BLUE;
		else whosTurn = Player.RED;
	}

	/**
	 * has the game started
	 * @return is game started
     */
	public boolean isStarted() { return started; }

	/**
	 * who's turn is it
	 * @return who's turn
     */
	public Player getWhosTurn() { return whosTurn; }

	/**
	 * which node is currently selected
	 * @return selected node
     */
	public Node getSelected() { return selected; }

	/**
	 * set selected node
	 * @param node new selected node
     */
	public void setSelected(Node node) { selected = node; }

	/**
	 * has a move been made this turn
	 * @return move made this turn
     */
	public boolean getIsMoveMade() { return isMoveMade; }

	/**
	 * is the game frame valid
	 * @return is frame valid
     */
	public boolean getIsValid() { return frame.getIsValid(); }

	/**
	 * get node that is invalid (or last invalid node)
	 * @return invalid node
     */
	public Node getInvalidNode() { return frame.getInvalidNode(); }

	/**
	 * has the player made move than one move this turn
	 * @return mutliple moves made
     */
	public boolean getAreMultipleMovesMade() { return areMultipleMovesMade; }

	/**
	 * get last move that was made
	 * @return last node moved
     */
	public Node getLastMove() { return lastMove; }

	/**
	 * get nodes with piece on them
	 * @return array of non empty nodes
     */
	public Node[] getNodes() {
		return frame.getNodes();
	}

	/**
	 * start a new game
	 */
	public void newGame() {
		started = true;
		randTurn();
		selected = Node.NONODE;
		areMultipleMovesMade = false;
		isMoveMade = false;
		frame = new Frame();
	}

	/**
	 * end current turn
	 */
	public void endTurn() {
		nextTurn();
		selected = Node.NONODE;
		areMultipleMovesMade = false;
		isMoveMade = false;
		frame.createRestorePoint();
	}

	/**
	 * move selected piece
	 * @param there where to move to
     */
	public void move(Node there) {
		if (isMoveMade) {
			lastMove = there;
			areMultipleMovesMade = true;
		}
		isMoveMade = true;
		frame.move(selected, there);
		selected = Node.NONODE;
	}

	// TODO for future implementation
	public void restore() {
		selected = Node.NONODE;
		frame.restore();
	}

}