public class Value {
	public int getInt() {
		return 0;
	}
	
	public boolean getBoolean() {
		return false;
	}
	//should NEVER be run in a node, only by the processor!
	public int getNode() {
		return -1;
	}
	
	public boolean isFromNode() {
		return false;
	}
}