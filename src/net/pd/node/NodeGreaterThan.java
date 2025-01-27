package net.pd.node;

import net.pd.value.*;

public class NodeGreaterThan extends Node {
	public NodeGreaterThan() {
		super(2);
	}
	
	public Value process(Value[] inputs) {
		boolean output = inputs[0].getInt() > inputs[1].getInt();
		return new ValueBoolean(output);
	}
}