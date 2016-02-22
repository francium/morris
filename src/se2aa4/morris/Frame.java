package se2aa4.morris;

public class Frame {

	private Node[] nodes, nodesCopy;
	
	private boolean isValid;
	private Node invalidNode;

	/**
	 * Frame object constructor
	 */
	public Frame() {
		isValid = true;
		nodes = new Node[16];
		// clear frame
		for (int i=0; i<nodes.length; i++) nodes[i] = Node.NONODE;
	}

	/**
	 * check if frame is valid
	 * @return is frame valid
     */
	public boolean getIsValid() {
		return isValid;
	}

	/**
	 * get invalid node
	 * @return invalid node
     */
	public Node getInvalidNode() {
		return invalidNode;
	}

	/**
	 * get an array of pieces that are on frame nodes
	 * @return array of piece (Nodes)
     */
	public Node[] getNodes() {
		int c=0;
		for (int i=0; i<nodes.length; i++) {
			if (nodes[i] != Node.NONODE) c++;
		}
		Node[] filledNodes = new Node[c];
		for (int i=0, j=0; i<nodes.length; i++) {
			if (nodes[i] != Node.NONODE) filledNodes[j++] = nodes[i];
		}
		return filledNodes;
	}

	/**
	 * move piece to another node on the frame
	 * @param node node to move
	 * @param there destination node
     */
	public void move(Node node, Node there) {
		if (nodes[there.getIndex()] != Node.NONODE) {
			// invalidate frame
			isValid = false;
            invalidNode = there;
        } else {
			nodes[there.getIndex()] = there;
		}
	}

	/**
	 * create restore point of game state
	 */
	public void createRestorePoint() {
		// TODO game state fields
		nodesCopy = clone(nodes);
	}

	/**
	 * restore game state
	 */
	public void restore() {
		// TODO game state fields
		nodes = clone(nodesCopy);
	}

	private static Node[] clone(Node[] array) {
		Node[] arrayCopy = new Node[array.length];
		for (int i=0; i<array.length;i++) {
			arrayCopy[i] = array[i];
		}
		return arrayCopy;
	}
}