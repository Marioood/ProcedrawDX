package net.pd.value;

public class ValueVariable extends Value {
	private String key;
	
	public ValueVariable(String key) {
		this.key = key;
		this.type = Value.VARIABLE;
	}
	
	public String getString() {
		return key;
	}
}