package se2aa4.morris;

public class Frame {
	
	private Node[] inventoryR, inventoryB;
	private Node[] nodes;

	public Frame() {
		inventoryB = new Node[] {Node.iR0, Node.iR1, Node.iR2, Node.iR3, Node.iR4, Node.iR5};
		inventoryB = new Node[] {Node.iB0, Node.iB1, Node.iB2, Node.iB3, Node.iB4, Node.iB5};
		nodes = new Node[16];
	}
}
