import javax.swing.*;
import java.awt.*;
import net.pd.node.*;
import net.pd.notex.*;
import java.util.ArrayList;

public class PanelGraph extends JPanel {
	
	private Node[] nodes;
	public float scale = 1;
	private Parser parser = new Parser();
	
	public PanelGraph(Node[] nodes) {
		this.nodes = nodes;
		
		this.setBounds(0, 256, 1024, 1024);
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setPaint(Color.white);

		float xStart = 32 * scale;
		float yStart = 16 * scale;
		//or 24 font size
		Font font = new Font("Times New Roman", Font.PLAIN, (int)(16 * scale));
		g2d.setFont(font);
		//render node interior
		for(int n = 0; n < nodes.length; n++) {
			ArrayList<Symbol> display = this.parser.parse(nodes[n].display);
			float xOffs = xStart;
			float yOffs = yStart;
			float nodeHeight = 32;
			
			for(int s = 0; s < display.size(); s++) {
				Symbol curSymbol = display.get(s);
				//render AND offset le symbol
				xOffs += this.drawSymbol(g2d, curSymbol, xOffs, yOffs, nodeHeight);
			}
			//render node top and bottom
			g2d.drawLine((int)xStart, (int)yOffs, (int)xOffs, (int)yOffs);
			g2d.drawLine((int)xStart, (int)(nodeHeight * scale + yOffs), (int)xOffs, (int)(nodeHeight * scale + yOffs));
			//offset by cap widths (they arent counted normally)
			//symbolWidths += 32 * scale;
			float middle = (xOffs - xStart) / 2;
			middle += xStart;
			//render output nub
			g2d.drawLine((int)(middle - 2 * scale), (int)(nodeHeight * scale + yOffs), (int)(middle), (int)(nodeHeight * scale + yOffs + 4 * scale));
			g2d.drawLine((int)(middle), (int)(nodeHeight * scale + yOffs + 4 * scale), (int)(middle + 2 * scale), (int)(nodeHeight * scale + yOffs));
			
			yStart += 64 * scale;
		}
	}
	
	//returned float is how wide the inputted symbol is
	private float drawSymbol(Graphics2D g2d, Symbol curSymbol, float xOffs, float yOffs, float nodeHeight) {
		//TODO: switch (?)
		if(curSymbol.type == Symbol.TEXT) {
			float baseline = (nodeHeight - 24) / 2 * scale;
			String str = ((SymbolText)curSymbol).text;
			g2d.drawString(str, xOffs, 16 * scale + baseline + yOffs);
			int stringWidth = g2d.getFontMetrics().stringWidth(str);
			return stringWidth;
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
			return symbol.width * scale;
		} else if(curSymbol.type == Symbol.INPUT) {
			SymbolInput input = (SymbolInput)curSymbol;
			
			float nameWidth = 0;
			//dont fuck around and dont find out
			if(input.name != null) {
				if(input.name.type == Symbol.INPUT) {
					System.out.println("WARNING: inputs cannot be inside inputs!!");
					return 0;
				}
				nameWidth = this.drawSymbol(g2d, input.name, xOffs, yOffs, nodeHeight);
			}
			//dont make inputs to thin
			nameWidth = Math.max(nameWidth, 24 * scale);
			xOffs += nameWidth / 2;
			//draw input nub
			g2d.drawLine((int)(xOffs - 2 * scale), (int)(yOffs), (int)(xOffs), (int)(yOffs - 4 * scale));
			g2d.drawLine((int)(xOffs), (int)(yOffs - 4 * scale), (int)(xOffs + 2 * scale), (int)(yOffs));
			
			float baseline = yOffs + 48 * scale;
			if(input.name == null) baseline = yOffs + 24 * scale;
			//draw input underline
			g2d.drawLine((int)(xOffs - nameWidth / 2), (int)(baseline), (int)(xOffs + nameWidth / 2), (int)(baseline));
			//recursion - scary!!
			return nameWidth;
		}
		//how would you do this?
		return 0;
	}
}