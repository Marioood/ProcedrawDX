package net.pd.node;

import net.pd.value.*;

public class NodePrint extends Node {
	public NodePrint() {
		super(1);
	}
	
	public Value process(Value[] inputs) {
		System.out.println(inputs[0].getString());
		return inputs[0];
	}
}