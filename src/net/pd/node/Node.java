package net.pd.node;

import net.pd.value.*;

public class Node {
	public Value[] inputs;
	public int inputCount;
	
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