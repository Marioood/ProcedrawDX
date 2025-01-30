package net.pd.node;

import net.pd.value.*;

public class NodeMultiply extends Node {
	public NodeMultiply() {
		super(2, "( i(0:number) $(times) i(1:number) )");
	}
	
	public Value process(Value[] inputs) {
		int output = inputs[0].getInt() * inputs[1].getInt();
		return new ValueInt(output);
	}
}