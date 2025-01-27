import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import net.pd.value.*;
import net.pd.node.*;

public class Procedraw {
	public static void main(String[] args) {
		//god java is so stupid what is this
		BufferedImage canvasData = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
		Interpreter interpreter = new Interpreter(canvasData);
		Window window = new Window();
		
		CanvasPanel canvasPanel = new CanvasPanel(canvasData);
		window.getContentPane().add(canvasPanel);
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