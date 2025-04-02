package net.pd.editor;
import java.awt.Graphics;

import javax.swing.*;

public class ComponentLine extends JComponent {
	private boolean hasFlippedX = false;
	private boolean hasFlippedY = false;
	
	public ComponentLine() {
		this.setOpaque(false);
	}

	public void paint(Graphics g) {
		super.paint(g);
		int x0, y0, x1, y1;
		
		if(this.hasFlippedX) {
			x0 = this.getWidth();
			x1 = 0;
		} else {
			x0 = 0;
			x1 = this.getWidth();
		}
		
		if(this.hasFlippedY) {
			y0 = this.getHeight();
			y1 = 0;
		} else {
			y0 = 0;
			y1 = this.getHeight();
		}
		
		g.drawLine(x0, y0, x1 - 1, y1 - 1);
		System.out.println("jojidpajopsd");
	}
	
	public void setEnds(int x0, int y0, int x1, int y1) {
		int xDif = x1 - x0;
		int yDif = y1 - y0;
		
		int width = Math.abs(xDif);
		int height = Math.abs(yDif);
		
		this.hasFlippedX = xDif < 0;
		this.hasFlippedY = yDif < 0;
		
		if(this.hasFlippedX) {
			x0 -= width;
		}
		if(this.hasFlippedY) {
			y0 -= height;
		}
		
		this.setBounds(x0, y0, width, height);
	}
}