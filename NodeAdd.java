public class NodeAdd extends Node {
	public NodeAdd() {
		super(2);
	}
	
	public Value process(Value[] inputs) {
		int output = inputs[0].getInt() + inputs[1].getInt();
		return new ValueInt(output);
	}
}