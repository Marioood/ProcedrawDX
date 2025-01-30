import javax.swing.*;
import java.awt.*;
import net.pd.node.*;
import net.pd.notex.*;
import java.util.ArrayList;

public class PanelGraph extends JPanel {
	
	private Node[] nodes;
	private ArrayList<Symbol> display;
	
	public PanelGraph(Node[] nodes) {
		this.nodes = nodes;
		
		this.setBounds(0, 256, 1024, 1024);
		
		this.display = new Parser().display;
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setPaint(Color.white);

		float scale = 2;
		float start = 16 * scale;
		float xOffs = start;
		float yOffs = start;
		float nodeHeight = 32;
		//or 24 font size
		Font font = new Font("Times New Roman", Font.PLAIN, (int)(16 * scale));
		g2d.setFont(font);
		//render node interior
		for(int s = 0; s < this.display.size(); s++) {
			Symbol curSymbol = this.display.get(s);
			if(curSymbol.type == Symbol.TEXT) {
				float baseline = (nodeHeight - 24) / 2 * scale;
				String str = ((SymbolText)curSymbol).text;
				g2d.drawString(str, xOffs, 16 * scale + baseline + yOffs);
				int stringWidth = g2d.getFontMetrics().stringWidth(str);
				xOffs += stringWidth;
			} else if(curSymbol.type == Symbol.VECTOR) {
				
				SymbolVector symbol = (SymbolVector)curSymbol;
				//render symbol
				float[][][] paths = symbol.paths;
				//go through paths
				for(int p = 0; p < paths.length; p++) {
					//go through points in path
					float[][] curPath = paths[p];
					float baseline = (nodeHeight - symbol.height) / 2 * scale;
					float yScale = scale;
					
					if(symbol.stretched) {
						yScale *= nodeHeight / symbol.height;
						baseline = 0;
					}
					
					for(int point = 0; point < curPath.length - 1; point++) {
						//draw lines between each point
						float[] curPoint = curPath[point];
						float[] nextPoint = curPath[point + 1];
						
						g2d.drawLine((int)(curPoint[0] * scale + xOffs), (int)(curPoint[1] * yScale + baseline + yOffs), (int)(nextPoint[0] * scale + xOffs), (int)(nextPoint[1] * yScale + baseline + yOffs));
					}
				}
				xOffs += symbol.width * scale;
			}
		}
		//render node top and bottom
		g2d.drawLine((int)start, (int)yOffs, (int)xOffs, (int)yOffs);
		g2d.drawLine((int)start, (int)(nodeHeight * scale + yOffs), (int)xOffs, (int)(nodeHeight * scale + yOffs));
		
		/*int[] xPoints = {10, 30, 40, 30, 10, 0};
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
			
		}*/
	}
	
	private void drawSymbol(Graphics2D g2d, SymbolVector symbol) {
	}
}