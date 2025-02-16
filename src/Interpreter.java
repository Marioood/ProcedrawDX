import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.image.*;
import net.pd.value.*;
import net.pd.node.*;
import java.util.ArrayList;

public class Interpreter {
	public ArrayList<Node> nodes = new ArrayList<Node>();
	public Hashtable<String, Value> variables = new Hashtable<String, Value>();
	private int safety = 0;
	private BufferedImage image;
	private int start;
	
	public Interpreter(BufferedImage image) {
		this.image = image;
		//0
		this.nodes.add(new NodeAdd()
			.setInput(0, new ValueInt(1))
			.setInput(1, new ValueVariable("y")));
		//1
		this.nodes.add(new NodeMultiply()
			.setInput(0, new ValueVariable("y"))
			.setInput(1, new ValueVariable("x")));
		//2
		this.nodes.add(new NodeAdd()
			.setInput(0, new ValueInt(1))
			.setInput(1, new ValueNode(0)));
		//3
		this.nodes.add(new NodeAdd()
			.setInput(0, new ValueNode(1))
			.setInput(1, new ValueNode(2)));
		//4
		this.nodes.add(new NodeGreaterThan()
			.setInput(0, new ValueNode(3))
			.setInput(1, new ValueInt(3)));
		//5
		this.nodes.add(new NodeEndRGB() 
			.setInput(0, new ValueNode(2))
			.setInput(1, new ValueNode(4))
			.setInput(2, new ValueInt(127)));
		//6
		this.nodes.add(new NodePrint() 
			.setInput(0, new ValueNode(2)));
		
		this.start = 5;
		
		/*this.nodes[0] = (new NodeOutputRGB()
			.setInput(0, new ValueVariable("x"))
			.setInput(1, new ValueVariable("y"))
			.setInput(2, new ValueInt(0))
		);*/
	}
	
	public void run() {
		Value xValue = new ValueInt(0);
		Value yValue = new ValueInt(0);
		this.variables.put("x", xValue);
		this.variables.put("y", yValue);
		
		for(int y = 0; y < 256; y++) {
			((ValueInt)yValue).setInt(y);
			for(int x = 0; x < 256; x++) {
				((ValueInt)xValue).setInt(x);
				//always start at the final output node
				int col = this.runNode(this.nodes.get(start)).getInt();
				image.setRGB(x, y, col);
			}
		}
	}
	
	private Value runNode(Node node) {
		/*if(this.safety > 100) {
			System.exit(1);
		}*/
		
		Value[] parsedInputs = new Value[node.inputs.length];
		//loop over all node inputs
		for(int i = 0; i < node.inputs.length; i++) {
			Value input = node.inputs[i];
			//do we need to calculate this input? or is it just typed there
			if(input.type == Value.NODE) {
				//run recursively
				//calculate new input
				parsedInputs[i] = this.runNode(this.nodes.get(input.getNode()));
			} else if(input.type == Value.VARIABLE) {
				//get variable value from hashtable
				parsedInputs[i] = this.variables.get(input.getString());
			} else {
				//input is typed, no need to calculate
				parsedInputs[i] = input;
			}
		}
		//return the node's output
		return node.process(parsedInputs);
	}
}