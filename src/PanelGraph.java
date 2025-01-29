import javax.swing.*;
import java.awt.*;
import net.pd.node.*;

public class PanelGraph extends JPanel {
	
	private Node[] nodes;
	
	public PanelGraph(Node[] nodes) {
		this.nodes = nodes;
		
		this.setBounds(0, 256, 512, 512);
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setPaint(Color.white);
		
		int[] xPoints = {10, 30, 40, 30, 10, 0};
		int[] yPoints = {0, 0, 10, 20, 20, 10};
		int yOffs = 32;
		
		for(int y = 0; y < nodes.length; y++) {
			if(nodes[y] == null) {
				break;
			}
			
			g2d.drawPolygon(xPoints, yPoints, 6);
			g2d.drawString(nodes[y].toString(), 10, 15 + y * yOffs);
			
			for(int i = 0; i < xPoints.length; i++) {
				yPoints[i] += yOffs; 
			}
			
		}
	}
}