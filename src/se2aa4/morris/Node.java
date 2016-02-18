package se2aa4.morris;

public enum Node {
	NONODE("noNode"),
	iR0("iR0"), iR1("iR1"), iR2("iR2"), iR3("iR3"), iR4("iR4"), iR5("iR5"), 
	iB0("iB0"), iB1("iB1"), iB2("iB2"), iB3("iB3"), iB4("iB4"), iB5("iB5"),
	nONW("nONW"), nON("nON"), nONE("nONE"), nOE("nOE"), NOSE("nOSE"), nOS("nOSE"), nOSW("nOSW"), nOW("nOW"),
	nINW("nINW"), nIN("nIN"), nINE("nINE"), nIE("nIE"), NISE("nISE"), nIS("nIS"), nISW("nISW"), nIW("nIW");
	
	private String id;
	
	private Node(String id) { this.id = id; }
	
	public String getId() { return this.id; }
}
