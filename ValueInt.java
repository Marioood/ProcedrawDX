public class ValueInt extends Value {
	private int value;
	
	public ValueInt(int value) {
		this.value = value;
	}
	
	public int getInt() {
		return this.value;
	}
	
	public boolean getBoolean() {
		return this.value == 0;
	}
}