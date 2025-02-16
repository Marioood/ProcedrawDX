package net.pd.value;

public class ValueNode extends Value {
	private int index;
	
	public ValueNode(int index) {
		this.index = index;
		this.type = Value.NODE;
	}
	
	public int getNode() {
		return index;
	}
}