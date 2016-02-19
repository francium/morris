package se2aa4.morris;

import java.util.ArrayList;

public class Frame {
	
	private ArrayList<Node> inventoryR, inventoryB, nodes;

	public Frame() {
		inventoryR = new ArrayList<Node>(6);
		inventoryR.add(Node.iR0);
		inventoryR.add(Node.iR1);
		inventoryR.add(Node.iR2);
		inventoryR.add(Node.iR3);
		inventoryR.add(Node.iR4);
		inventoryR.add(Node.iR5);

		inventoryB = new ArrayList<Node>(6);
		inventoryR.add(Node.iB0);
		inventoryR.add(Node.iB1);
		inventoryR.add(Node.iB2);
		inventoryR.add(Node.iB3);
		inventoryR.add(Node.iB4);
		inventoryR.add(Node.iB5);

		nodes = new ArrayList<Node>(16);
	}
	
	public void move(Node node, Node there) {
		if (node.getId().contains("R")) {
			inventoryR.remove(node);
			nodes.add(node);
		} else if (node.getId().contains("B")) {
			inventoryB.remove(node);
			nodes.add(node);
		}
	}
}