package net.pd.node;

import net.pd.value.*;

public class NodeOutputRGB extends Node {
	public NodeOutputRGB() {
		super(3);
	}
	
	public Value process(Value[] inputs) {
		int r = inputs[0].getInt() << 16;
		int g = inputs[1].getInt() << 8;
		int b = inputs[2].getInt();
		int rgb = 0xff000000 | r | g | b;
		//System.out.println(rgb);
		return new ValueInt(rgb);
	}
}