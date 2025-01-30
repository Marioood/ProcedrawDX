package net.pd.node;

import net.pd.value.*;

public class Node {
	//the nodes inputs... obviously
	public Value[] inputs;
	//how many inputs the node has
	public int inputCount;
	//script used to render node
	public String display = "( i(0) 'f' i(1) )";
	//ternary "( i(0:boolean) $(question) i(1:any) $(colon) i(2:any) )";
	//whether this node is the end of a node graph
	public boolean terminates = false;
	
	public Node(int inputCount, String display) {
		this.inputs = new Value[inputCount];
		this.inputCount = inputCount;
		this.display = display;
	}
	
	public Node setInput(int idx, Value output) {
		this.inputs[idx] = output;
		return this;
	}
	
	public Value process(Value[] inputs) {
		return null;
	}
}