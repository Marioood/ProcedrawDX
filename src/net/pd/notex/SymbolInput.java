package net.pd.notex;

public class SymbolInput extends Symbol {
	
	public int index;
	public Symbol name;
	public float x;
	
	public SymbolInput(int index, Symbol name) {
		//offset for connections? i guess naybe
		//TODO: symbol name and type
		this.type = Symbol.INPUT;
		this.index = index;
		this.name = name;
	}
}
