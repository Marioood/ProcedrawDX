package net.pd.editor;

public class ComponentConnection extends ComponentLine {
	public PanelNode input;
	public PanelNode output;
	
	public ComponentConnection(PanelNode input, PanelNode output) {
		super();
		this.input = input;
		this.output = output;
	}
	
	public void resize() {
		this.setEnds(this.input.getX(), this.input.getY(), this.output.getX(), this.output.getY());
		//this.paint(this.getGraphics());
		//System.out.println("i am being resized");
	}
}
