package se2aa4.morris;

public class Frame {

	private Node[] nodes, nodesCopy;
	
	private boolean isValid;
	private Node invalidNode;

	public Frame() {
		isValid = true;
		nodes = new Node[16];
		// clear frame
		for (int i=0; i<nodes.length; i++) nodes[i] = Node.NONODE;
	}
	
	public boolean getIsValid() {
		return isValid;
	}

	public Node getInvalidNode() {
		return invalidNode;
	}

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
	
	public void move(Node node, Node there) {
		if (nodes[there.getIndex()] != Node.NONODE) {
			// invalidate frame
			isValid = false;
            invalidNode = there;
        } else {
			nodes[there.getIndex()] = there;
		}
	}
	
	public void createRestorePoint() {
		nodesCopy = clone(nodes);
	}
	
	public void restore() {
		nodes = clone(nodesCopy);
	}

	public static Node[] clone(Node[] array) {
		Node[] arrayCopy = new Node[array.length];
		for (int i=0; i<array.length;i++) {
			arrayCopy[i] = array[i];
		}
		return arrayCopy;
	}
}