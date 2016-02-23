package se2aa4.morris;

/**
 * Enumerator of Nodes (and Pieces)
 * @author Varun Hooda 001419242
 * @author Aushim Lakhana 001201528
 * @author Matthew Shortt 001417616
 * @version 1.0
 */
public enum Node {
	NONODE("noNode"), INVALID("invalid"),
	iR0("iR0"), iR1("iR1"), iR2("iR2"), iR3("iR3"), iR4("iR4"), iR5("iR5"),
	iB0("iB0"), iB1("iB1"), iB2("iB2"), iB3("iB3"), iB4("iB4"), iB5("iB5"),
	nONW(0, "nONW"), nON(1, "nON"), nONE(2, "nONE"), nOE(3, "nOE"), nOSE(4, "nOSE"), nOS(5, "nOS"), nOSW(6, "nOSW"), nOW(7, "nOW"),
	nINW(8, "nINW"), nIN(9, "nIN"), nINE(10, "nINE"), nIE(11, "nIE"), nISE(12, "nISE"), nIS(13, "nIS"), nISW(14, "nISW"), nIW(15, "nIW");

	private String id;
	private int index;

	Node(int index, String id) {
		this.id = id;
		this.index = index;
	}

	Node(String id) {
		this.id = id;
		this.index = -1;
	}

	public String getId() { return this.id; }

	public int getIndex() { return this.index; }
}
