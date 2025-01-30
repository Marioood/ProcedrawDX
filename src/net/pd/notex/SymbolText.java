package net.pd.notex;

public class SymbolText extends Symbol {
	
	public String text;
	
	public SymbolText(String text) {
		this.type = Symbol.TEXT;
		this.text = text;
	}
}
