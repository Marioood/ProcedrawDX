package net.pd.node;

import net.pd.value.*;

public class NodeEndRGB extends Node {
	public NodeEndRGB() {
		super(3, "[ i(0:number:r) i(1:number:g) i(2:number:b) ]");
		this.terminates = true;
	}
	
	public Value process(Value[] inputs) {
		//convert inputs to ARGB int
		//TODO: limit to 0 - 255
		int r = inputs[0].getInt() << 16;
		int g = inputs[1].getInt() << 8;
		int b = inputs[2].getInt();
		int rgb = 0xff000000 | r | g | b;
		
		return new ValueInt(rgb);
	}
}