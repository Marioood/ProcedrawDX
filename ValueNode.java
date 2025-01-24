public class ValueNode extends Value {
	private int index;
	
	public ValueNode(int index) {
		this.index = index;
	}
	
	public int getNode() {
		return index;
	}
	
	public boolean isFromNode() {
		return true;
	}
}