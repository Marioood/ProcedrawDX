package net.pd.value;

public class ValueVariable extends Value {
	private String key;
	
	public ValueVariable(String key) {
		this.key = key;
	}
	
	public String getString() {
		return key;
	}
	
	public boolean isFromVariable() {
		return true;
	}
}