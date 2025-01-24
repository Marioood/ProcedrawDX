public class Interpreter {
	public Node[] nodes = new Node[6];
	private int safety = 0;
	
	public Interpreter() {
		this.nodes[0] = (new NodeAdd()
			.setInput(0, new ValueInt(1))
			.setInput(1, new ValueInt(1)));
		this.nodes[1] = (new NodeMultiply()
			.setInput(0, new ValueInt(7))
			.setInput(1, new ValueInt(6)));
		this.nodes[2] = (new NodeAdd()
			.setInput(0, new ValueInt(1))
			.setInput(1, new ValueNode(0)));
		this.nodes[3] = (new NodeAdd()
			.setInput(0, new ValueNode(1))
			.setInput(1, new ValueNode(2)));
		this.nodes[4] = (new NodeGreaterThan()
			.setInput(0, new ValueNode(3))
			.setInput(1, new ValueInt(3)));
		this.nodes[5] = (new NodePrint()
			.setInput(0, new ValueNode(4)));
		
		this.runNode(this.nodes[5]);
		
		/*for(int n = 0; n < 6; n++) {
			System.out.println(n);
			Node currentNode = nodes[n];
			//loop over inputs
			for(int i = 0; i < currentNode.inputs.length; i++) {
				System.out.println(currentNode.inputs[i]);
			}
		}*/
	}
	
	private Value runNode(Node node) {
		if(this.safety > 100) {
			System.exit(1);
		}
		
		Value[] parsedInputs = new Value[node.inputs.length];
		//loop over all node inputs
		for(int i = 0; i < node.inputs.length; i++) {
			Value input = node.inputs[i];
			//do we need to calculate this input? or is it just typed there
			if(input.isFromNode()) {
				//run recursively
				//calculate new input
				parsedInputs[i] = this.runNode(this.nodes[input.getNode()]);
			} else {
				//input is typed, no need to calculate
				parsedInputs[i] = input;
			}
		}
		//return the node's output
		return node.process(parsedInputs);
	}
}