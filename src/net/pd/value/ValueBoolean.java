package net.pd.value;

public class ValueBoolean extends Value {
	private boolean value;
	
	public ValueBoolean(boolean value) {
		this.value = value;
	}
	
	public int getInt() {
		return this.value ? 1 : 0;
	}
	
	public boolean getBoolean() {
		return this.value;
	}
	
	public String getString() {
		return this.value ? "true" : "false";
	}
}