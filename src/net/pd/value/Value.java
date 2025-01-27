package net.pd.value;

public class Value {
	public int getInt() {
		return 0;
	}
	
	public boolean getBoolean() {
		return false;
	}
	
	public String getString() {
		return "null";
	}
	//should NEVER be called in a node, only by the processor!
	public int getNode() {
		return -1;
	}
	
	public boolean isFromNode() {
		return false;
	}
	
	public boolean isFromVariable() {
		return false;
	}
}