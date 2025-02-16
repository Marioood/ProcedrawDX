package net.pd.value;

public class ValueInt extends Value {
	private int value;
	
	public ValueInt(int value) {
		this.value = value;
		this.type = Value.INTEGER;
	}
	
	public int getInt() {
		return this.value;
	}
	
	public boolean getBoolean() {
		return this.value == 0;
	}
	
	public String getString() {
		return Integer.toString(this.value);
	}
	
	public void setInt(int newValue) {
		this.value = newValue;
	}
}