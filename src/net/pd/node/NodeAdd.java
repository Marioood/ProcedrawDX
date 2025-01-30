package net.pd.node;

import net.pd.value.*;

public class NodeAdd extends Node {
	public NodeAdd() {
		super(2, "( i(0:number) '+' i(1:number) )");
	}
	
	public Value process(Value[] inputs) {
		int output = inputs[0].getInt() + inputs[1].getInt();
		return new ValueInt(output);
	}
}