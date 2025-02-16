import javax.swing.*;
import java.awt.*;
import net.pd.node.*;
import net.pd.notex.*;
import java.util.ArrayList;
import net.pd.value.*;

public class PanelGraph extends JPanel {
	
	private ArrayList<Node> nodes;
	private Parser parser = new Parser();
	
	public float scale = 2f;
	public float camX = 0f;
	public float camY = 0f;
	public Font font =  new Font("Times New Roman", Font.PLAIN, (int)(16 * scale));
	
	public PanelGraph(ArrayList<Node> nodes) {
		this.nodes = nodes;
		
		this.setBounds(0, 256, 1024, 1024);
		
		for(Node node : this.nodes) {
			this.add(new PanelNode(node, this, this.parser.parse(node.display)));
		}
		this.setBackground(Color.GRAY);
	}

	public void paint(Graphics g) {
		super.paint(g);
	}
}