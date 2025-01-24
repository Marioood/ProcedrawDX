public class Node {
	public Value[] inputs;
	public int inputCount;
	//inputs should be outputs of other nodes (nodes can have more than 1 output)
	public Node(int inputCount) {
		this.inputs = new Value[inputCount];
		this.inputCount = inputCount;
	}
	
	public Node setInput(int idx, Value output) {
		this.inputs[idx] = output;
		return this;
	}
	
	public Value process(Value[] inputs) {
		return null;
	}
}