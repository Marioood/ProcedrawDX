package net.pd.node;

import net.pd.notex.Symbol;
import net.pd.value.*;

public class Node {
	/*----V RUNNING V-----*/
	
	//the nodes inputs... obviously
	public Value[] inputs;
	//how many inputs the node has
	public int inputCount;
	//whether this node is the end of a node graph
	public boolean terminates = false;
	
	/*----V RENDERING V-----*/
	
	//script used to render node
	public String display = "( i(0) 'f' i(1) )";
	//parsed version of the render script
	//public Symbol[] displayParsed;
	//ternary "( i(0:boolean) $(question) i(1:any) $(colon) i(2:any) )";
	
	public Node(int inputCount, String display) {
		this.inputs = new Value[inputCount];
		this.inputCount = inputCount;
		this.display = display;
		//this.displayParsed = this.parser.parse(this.display);
	}
	
	public Node setInput(int idx, Value output) {
		this.inputs[idx] = output;
		return this;
	}
	
	public Value process(Value[] inputs) {
		return null;
	}
}