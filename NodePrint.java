public class NodePrint extends Node {
	public NodePrint() {
		super(1);
	}
	
	public Value process(Value[] inputs) {
		System.out.println(inputs[0]);
		System.out.println(inputs[0].getBoolean());
		return null;
	}
}