package net.pd.value;

public class Value {
	public int type;
	//yada yada i should use enums SHUT UP
	public static final int INTEGER = 0;
	public static final int FIXED = 1;
	public static final int BOOLEAN = 2;
	public static final int STRING = 4;
	public static final int NODE = 8;
	public static final int VARIABLE = 16;
	
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
}