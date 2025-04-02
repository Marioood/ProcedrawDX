import javax.swing.*;
import java.awt.image.*;
import net.pd.value.*;
import net.pd.editor.PanelCanvas;
import net.pd.editor.PanelGraph;
import net.pd.editor.Window;
import net.pd.node.*;
import net.pd.notex.*;

public class Procedraw {
	public static void main(String[] args) {
		BufferedImage canvasData = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
		//god java is so stupid what is this
		Interpreter interpreter = new Interpreter(canvasData);
		interpreter.run();
		Window window = new Window();
		
		PanelCanvas panelCanvas = new PanelCanvas(canvasData);
		window.getContentPane().add(panelCanvas);
		
		PanelGraph panelGraph = new PanelGraph(interpreter.nodes);
		window.getContentPane().add(panelGraph);
		
		JTextField textField = new JTextField(10);
		textField.setBounds(0, 0, 24, 16);
		panelGraph.add(textField);
		
		
		//Parser parser = new Parser();
		/*long mantissa = 16;
		long x = 0x00008000;//5 << mantissa;
		long y = 8 << mantissa;
		System.out.println((x + y) >> mantissa);
		System.out.println((x - y) >> mantissa);
		System.out.println("---");
		System.out.println(x);
		System.out.println(y);
		//multiplication needs extra scaling
		System.out.println((x * y) >> (mantissa << 1));*/
		//to get fractional part - fraction/2^16
		
		//System.out.println("---");
		//System.out.println((x / y) >> (mantissa >> 1));
		//System.out.println((x % y) >> mantissa);
		
	}
}