package net.pd.editor;
import net.pd.node.*;
import net.pd.notex.Symbol;
import net.pd.notex.SymbolInput;
import net.pd.notex.SymbolText;
import net.pd.notex.SymbolVector;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.event.MouseListener;

import javax.swing.*;

public class PanelNode extends JPanel implements MouseMotionListener, MouseListener {
	
	public Node tiedNode;
	//the PanelGraph that this PanelNode belongs to
	public PanelGraph parent;
	//position
	private boolean beingDragged = false;
	private int mouseOffsX = 0;
	private int mouseOffsY = 0;
	//display
	public float width;
	public float height;
	private ArrayList<Symbol> display;
	private boolean isFirstRender = true;
	private float scale;
	
	private final float nubSize = 4;
	public final float minInputWidth = 24;
	
	private ComponentLine outputSelect;
	private boolean draggingOutput = false;

	public PanelNode(Node node, PanelGraph parent, ArrayList<Symbol> display) {
		this.tiedNode = node;
		this.parent = parent;
		this.display = display;
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.scale = this.parent.scale;
		//this.getGraphics().setFont(this.parent.font);
		this.setBounds(256, 257, 256, 256);
		this.setOpaque(false);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(!this.beingDragged) {
			this.mouseOffsX = this.getX() - e.getXOnScreen();
			this.mouseOffsY = this.getY() - e.getYOnScreen();
		}
		
		int mouseX = e.getXOnScreen() + this.mouseOffsX;
		int mouseY = e.getYOnScreen() + this.mouseOffsY;
		
		boolean isInsideNode = e.getY() > (int)(this.nubSize * this.scale) && e.getY() < (int)((this.height + this.nubSize) * this.scale);
		
		float nubX = (this.width / 2) * scale;
		float nubY = (this.height + this.nubSize) * scale;
		
		if(this.draggingOutput) {
			this.outputSelect.setEnds((int)nubX + this.getX(), (int)nubY + this.getY(), mouseX + this.getWidth() / 2, mouseY + this.getHeight());
		} else if(isInsideNode || this.beingDragged) {
			//TODO: broken
			this.setLocation(mouseX, mouseY);
			this.beingDragged = true;
		} else {
			float xDif = nubX - (float)e.getX();
			float yDif = nubY - (float)e.getY();
			float outputDist = (float)Math.sqrt(xDif * xDif + yDif * yDif);
			
			if(outputDist < this.nubSize * scale) {
				if(!this.beingDragged) {
					this.outputSelect = new ComponentLine();
					this.parent.add(this.outputSelect);
					this.draggingOutput = true;
					this.beingDragged = true;
				}
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		//drag or drag output
		this.beingDragged = false;
		this.draggingOutput = false;
		//delete visual line
		if(this.outputSelect != null) {
			//does this cause a memory leak? who knows!
			this.parent.remove(this.outputSelect);
			PanelNode inputCandidate = this.parent.panelNodeUnderMouse;
			
			if(inputCandidate != null && inputCandidate != this) {
				//set one of my inputs to another node's output
				System.out.println(this.tiedNode + " -> " + inputCandidate.tiedNode);
				this.parent.connectionLines.add(new ComponentConnection(inputCandidate, this));
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		//am i about to recieve input from another nodepanel?
		this.parent.panelNodeUnderMouse = this;
	}
	
	private void initializeDisplay(Graphics g) {
		//account for end caps
		this.width = 32;
		//starting height
		this.height = 32;
		
		for(int s = 0; s < this.display.size(); s++) {
			Symbol curSymbol = this.display.get(s);
			
			if(curSymbol.type == Symbol.TEXT) {
				String str = ((SymbolText)curSymbol).text;
				float stringWidth = (float)g.getFontMetrics().stringWidth(str);
				//string width is already scaled (based off of font use for rendering)
				this.width += stringWidth / scale;
			} else if(curSymbol.type == Symbol.VECTOR) {
				SymbolVector symbol = (SymbolVector)curSymbol;
				this.width += symbol.width;
			} else if(curSymbol.type == Symbol.INPUT) {
				SymbolInput input = (SymbolInput)curSymbol;
				float nameWidth = 0;
				//dont fuck around and dont find out
				if(input.name != null) {
					//copy + paste!!
					if(input.name.type == Symbol.TEXT) {
						String str = ((SymbolText)input.name).text;
						float stringWidth = (float)g.getFontMetrics().stringWidth(str);
						//string width is already scaled (based off of font use for rendering)
						nameWidth = stringWidth / scale;
					} else if(input.name.type == Symbol.VECTOR) {
						SymbolVector symbol = (SymbolVector)input.name;
						nameWidth = symbol.width;
					}
				}
				
				this.width += Math.max(nameWidth, this.minInputWidth);
			} else if(curSymbol.type == Symbol.NEWLINE) {
				this.height += 32;
			}
		}
		this.setSize((int)(this.width * scale + 1), (int)((this.height + nubSize * 2) * scale + 1));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		//or 24 font size
		g2d.setFont(this.parent.font);
		
		if(this.isFirstRender) {
			//resize hack because this.getGraphics() returns null in the constructor!
			this.initializeDisplay(g2d);
			this.isFirstRender = false;
		}
		
		g2d.setPaint(Color.white);
		//ArrayList<ArrayList<Float>> inputsX = new ArrayList<ArrayList<Float>>();
		
		//render node interior
			//inputsX.add(new ArrayList<Float>());
			
			//node.x = rand.nextFloat() * 512;
			//node.y += 48 * scale * n;
		float xOffs = 16 * scale;
		float symbolOffs = 0;
		//input nub padding
		float yOffs = 4 * scale;
			
		for(int s = 0; s < display.size(); s++) {
			Symbol curSymbol = display.get(s);
			//render AND offset le symbol
			symbolOffs += this.drawSymbol(g2d, curSymbol, xOffs + symbolOffs, yOffs);
			//add found inputs to list
			//TODO: could be done during parsing? less janky me thinks
			//if(curSymbol.type == Symbol.INPUT) {
				//inputsX.get(n).add(symbolOffs);
			//}
		}
		//render node top and bottom
		g2d.drawLine((int)xOffs, (int)yOffs, (int)(xOffs + symbolOffs), (int)yOffs);
		g2d.drawLine((int)xOffs, (int)(this.height * scale + yOffs), (int)(xOffs + symbolOffs), (int)(this.height * scale + yOffs));
		//offset by cap widths (they arent counted normally)
		//symbolWidths += 32 * scale;
		float middle = symbolOffs / 2;
		middle += xOffs;
		//render output nub
		g2d.drawLine((int)(middle - 2 * scale), (int)(this.height * scale + yOffs), (int)(middle), (int)(this.height * scale + yOffs + 4 * scale));
		g2d.drawLine((int)(middle), (int)(this.height * scale + yOffs + 4 * scale), (int)(middle + 2 * scale), (int)(this.height * scale + yOffs));

		
		/*for(int i = 0; i < node.inputs.length; i++) {
			Value input = node.inputs[i];
			//havet to convert cause fuck you
			//float inputX = inputsX.get(n).get(i).floatValue();
			//float inputY = inputsY.get(n).get(i).floatValue();
			//g2d.drawLine((int)inputX, (int)inputY, 0, 0);
			//skip if the input aint from a node
			if(input.type != Value.NODE) continue;
			
			Node inputNode = nodes[input.getNode()];
			g2d.drawLine((int)node.x, (int)node.y, (int)inputNode.x, (int)inputNode.y);
			System.out.println(n + ": " + i);
			System.out.println(node.y);
			}*/
	}

	//returned float is how wide the inputted symbol is
	private float drawSymbol(Graphics2D g2d, Symbol curSymbol, float xOffs, float yOffs) {
		if(curSymbol.type == Symbol.TEXT) {
			float baseline = (this.height - 24) / 2 * scale;
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
				float baseline = (this.height - symbol.height) / 2 * scale;
				float yScale = scale;
				
				if(symbol.stretched) {
					yScale *= this.height / symbol.height;
					baseline = 0;
				}
				
				for(int point = 0; point < curPath.length - 1; point++) {
					//draw lines between each point
					float[] curPoint = curPath[point];
					float[] nextPoint = curPath[point + 1];
					
					g2d.drawLine((int)(curPoint[0] * scale + xOffs), (int)(curPoint[1] * yScale + baseline + nubSize * scale), (int)(nextPoint[0] * scale + xOffs), (int)(nextPoint[1] * yScale + baseline + yOffs));
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
				//recursion -- scary!!
				nameWidth = this.drawSymbol(g2d, input.name, xOffs, yOffs);
			}
			//dont make inputs to thin
			nameWidth = Math.max(nameWidth, this.minInputWidth * scale);
			xOffs += nameWidth / 2;
			//draw input nub
			g2d.drawLine((int)(xOffs - 2 * scale), (int)(yOffs), (int)(xOffs), (int)(yOffs - 4 * scale));
			g2d.drawLine((int)(xOffs), (int)(yOffs - 4 * scale), (int)(xOffs + 2 * scale), (int)(yOffs));
			
			float baseline = yOffs + 48 * scale;
			if(input.name == null) baseline = yOffs + 24 * scale;
			//draw input underline
			g2d.drawLine((int)(xOffs - nameWidth / 2), (int)(baseline), (int)(xOffs + nameWidth / 2), (int)(baseline));
			return nameWidth;
		}
		//unreachable
		return 0;
	}
	//unused
	@Override
	public void mouseMoved(MouseEvent e) { }
	@Override
	public void mouseClicked(MouseEvent e) { }
	@Override
	public void mousePressed(MouseEvent e) { }
	@Override
	public void mouseExited(MouseEvent e) { }
}